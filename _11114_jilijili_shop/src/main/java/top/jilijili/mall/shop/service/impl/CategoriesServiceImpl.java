package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.common.entity.Item;
import top.jilijili.common.entity.Result;
import top.jilijili.module.utils.TreeUtils;
import top.jilijili.mall.shop.mapper.CategoriesMapper;
import top.jilijili.mall.shop.service.CategoriesService;
import top.jilijili.module.pojo.dto.shop.CategoriesDto;
import top.jilijili.module.pojo.entity.shop.Categories;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author admin
 * @description 针对表【shop_categories(商品分类表)】的数据库操作Service实现
 * @createDate 2023-08-26 18:55:20
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories>
        implements CategoriesService {


    /**
     * 添加或者修改商品分类
     *
     * @param categoryDto 操作对象
     * @return 操作后的对象
     */
    @Override
    public Result<Categories> addOrUpdate(CategoriesDto categoryDto) {
        Categories categories = new Categories();
        BeanUtils.copyProperties(categoryDto, categories);
        if (StringUtils.hasText(categoryDto.getCategoryId())) {
            categories.setCategoryId(String.valueOf(Long.valueOf(categoryDto.getCategoryId())));
        }

        // 同步数据库
        return this.saveOrUpdate(categories) ? Result.ok(categories, "操作成功") : Result.ok(categories, "操作失败");
    }

    /**
     * 分页查询商品类列表
     *
     * @param categoryDto
     * @return label, value object a list
     */
    public Result<IPage<Item>> getCategoriesListDict(CategoriesDto categoryDto) {
        Page<Categories> page = this.lambdaQuery()
                .page(new Page<>(categoryDto.getPage(), categoryDto.getSize()));

        IPage<Item> iPage = page.convert(categories -> Item.builder()
                .value(String.valueOf(categories.getCategoryId()))
                .label(categories.getCategoryName())
                .build());
        return Result.ok(iPage);
    }


    /**
     * 分页查询商品分类列表
     *
     * @param dto
     * @return 分类列表
     */
    @Override
    public Result<Page<Categories>> getCategoriesList(CategoriesDto dto) {
        Page<Categories> page = this.lambdaQuery()
                .eq(StringUtils.hasText(dto.getCategoryId()), Categories::getParentId, dto.getCategoryId())
                .like(StringUtils.hasText(dto.getCategoryName()), Categories::getCategoryName, dto.getCategoryName())
                .between(dto.getCreatedTime() != null, Categories::getCreatedTime, dto.getCreatedTime(), dto.getComparisonTime())
                .orderByDesc(Categories::getCreatedTime)
                .page(new Page<>(dto.getPage(), dto.getSize() + 100L));
        List<Categories> records = page.getRecords();

        for (Categories cate : records) {
            cate.setLabel(cate.getCategoryName());
        }

        // 查询上级分类
        if (StringUtils.hasText(dto.getCategoryId())) {
            return Result.ok(page);
        }

        // 找到顶级分类
        List<Categories> parentList = records.stream().filter(item -> item.getParentId().equals("0")).toList();

        // 递归设置子分类
        for (Categories categories : parentList) {
            categories.editChildNodes(TreeUtils.buildTree(categories.getCategoryId(), records));
        }
        return Result.ok(page.setRecords(parentList));
    }

    /**
     * 递归设置子分类
     *
     * @param categoryId 当前分类id
     * @param records    所有记录
     * @return 子分类列表
     */
    private List<Categories> getChild(String categoryId, List<Categories> records) {
        List<Categories> childList = new ArrayList<>();
        for (Categories cat : records) {
            if (categoryId.equals(cat.getParentId())) {
                childList.add(cat);
                childList.addAll(getChild(cat.getCategoryId(), records));
            }
        }
        if (childList.isEmpty()) {
            return Collections.emptyList();
        }
        return childList;
    }

    /**
     * 递归设置分类树
     *
     * @param parentId 当前父类的ID
     * @param records  所有的分类记录
     * @return 分类树
     */
    private List<Categories> buildTree(String parentId, List<Categories> records) {
        List<Categories> childCategories = new ArrayList<>();
        for (Categories category : records) {
            if (category.getParentId().equals(parentId)) {

                List<Categories> subCategories = buildTree(category.getCategoryId(), records);

                category.setChildren(subCategories);

                childCategories.add(category);
            }
        }
        return childCategories;
    }

}




