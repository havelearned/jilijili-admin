package top.jilijili.mall.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.common.entity.Item;
import top.jilijili.common.entity.Result;
import top.jilijili.common.utils.TreeUtils;
import top.jilijili.mall.shop.mapper.CategoriesMapper;
import top.jilijili.mall.shop.service.CategoriesService;
import top.jilijili.module.pojo.dto.blog.CategoryDto;
import top.jilijili.module.pojo.dto.shop.CategoriesDto;
import top.jilijili.module.pojo.entity.shop.Categories;

import java.util.ArrayList;
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
    public Result<Categories> addOrUpdate(CategoryDto categoryDto) {
        Categories categories = Categories.builder()
                .categoryName(categoryDto.getTitle())
                .createdTime(categoryDto.getCreatedTime())
                .updatedTime(categoryDto.getUpdatedTime())
                .build();
        if (StringUtils.hasText(categoryDto.getCategoryId())) {
            categories.setCategoryId(String.valueOf(Long.valueOf(categoryDto.getCategoryId())));
        }

        // 同步数据库
        this.saveOrUpdate(categories);
        return Result.ok(categories);
    }

    /**
     * 分页查询商品类列表
     *
     * @param categoryDto
     * @return label, value object a list
     */
    public Result<IPage<Item>> getCategoriesListDict(CategoriesDto categoryDto) {
        Result<Page<Categories>> categoriesList = this.getCategoriesList(categoryDto);
        Page<Categories> page = categoriesList.getData();
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
                .eq(StringUtils.hasText(dto.getCategoryId()), Categories::getCategoryId, dto.getCategoryId())
                .like(StringUtils.hasText(dto.getCategoryName()), Categories::getCategoryName, dto.getCategoryName())
                .between(dto.getCreatedTime() != null, Categories::getCreatedTime, dto.getCreatedTime(), dto.getComparisonTime())
                .orderByDesc(Categories::getCreatedTime)
                .page(new Page<>(dto.getPage(), dto.getSize()));
        List<Categories> records = page.getRecords();
        // 顶级父类
        List<Categories> parentList = records.stream().filter(item -> item.getParentId().equals("0")).toList();

        for (Categories categories : parentList) {
            categories.editChildNodes(TreeUtils.buildTree(categories.getCategoryId(), records));
        }
        return Result.ok(page.setRecords(parentList));
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

                category.setChilendList(subCategories);

                childCategories.add(category);
            }
        }
        return childCategories;
    }

}




