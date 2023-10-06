package top.jilijili.shop.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import top.jilijili.common.entity.Item;
import top.jilijili.common.entity.Result;
import top.jilijili.module.entity.Categories;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.module.entity.dto.CategoryDto;

/**
* @author admin
* @description 针对表【shop_categories(商品分类表)】的数据库操作Service
* @createDate 2023-08-26 18:55:20
*/
public interface CategoriesService extends IService<Categories> {

    /**
     * 分页查询商品分类列表
     *
     * @param categoryDto
     * @return 分类列表
     */
    Result<Page<Categories>> getCategoriesList(CategoryDto categoryDto);

    /**
     * 分页查询商品类字典列表
     *
     * @param categoryDto
     * @return label, value object a list
     */
    Result<IPage<Item>> getCategoriesListDict(CategoryDto categoryDto);

    /**
     * 添加或者修改商品分类
     * @param categoryDto 操作对象
     * @return 操作后的对象
     */
    Result<Categories> addOrUpdate(CategoryDto categoryDto);
}
