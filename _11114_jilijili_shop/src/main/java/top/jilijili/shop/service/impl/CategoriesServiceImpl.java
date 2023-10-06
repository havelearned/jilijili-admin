package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.common.entity.Item;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.Categories;
import top.jilijili.module.entity.dto.CategoryDto;
import top.jilijili.shop.mapper.CategoriesMapper;
import top.jilijili.shop.service.CategoriesService;

/**
 * @author admin
 * @description 针对表【shop_categories(商品分类表)】的数据库操作Service实现
 * @createDate 2023-08-26 18:55:20
 */
@Service
public class CategoriesServiceImpl extends ServiceImpl<CategoriesMapper, Categories>
        implements CategoriesService {

    /**
     * 分页查询商品分类列表
     *
     * @param categoryDto
     * @return 分类列表
     */
    @Override
    public Result<Page<Categories>> getCategoriesList(CategoryDto categoryDto) {
        Page<Categories> page = this.lambdaQuery()
                .eq(StringUtils.hasText(categoryDto.getCategoryId()), Categories::getCategoryId, categoryDto.getCategoryId())
                .like(StringUtils.hasText(categoryDto.getTitle()), Categories::getCategoryName, categoryDto.getTitle())
                .between(categoryDto.getCreatedTime() != null, Categories::getCreatedTime, categoryDto.getCreatedTime(), categoryDto.getComparisonTime())
                .orderByDesc(Categories::getCreatedTime)
                .page(new Page<>(categoryDto.getPage(), categoryDto.getSize()));
        return Result.ok(page);
    }

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
            categories.setCategoryId(Long.valueOf(categoryDto.getCategoryId()));
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
    public Result<IPage<Item>> getCategoriesListDict(CategoryDto categoryDto) {
        Result<Page<Categories>> categoriesList = this.getCategoriesList(categoryDto);
        Page<Categories> page = categoriesList.getData();
        IPage<Item> iPage = page.convert(categories -> Item.builder()
                .value(String.valueOf(categories.getCategoryId()))
                .label(categories.getCategoryName())
                .build());
        return Result.ok(iPage);
    }
}




