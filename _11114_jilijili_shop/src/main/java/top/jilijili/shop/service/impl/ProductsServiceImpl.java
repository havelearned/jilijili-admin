package top.jilijili.shop.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.shop.entity.Products;
import top.jilijili.shop.service.ProductsService;
import top.jilijili.shop.mapper.ProductsMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【shop_products(商品表)】的数据库操作Service实现
* @createDate 2023-08-26 18:55:20
*/
@Service
public class ProductsServiceImpl extends ServiceImpl<ProductsMapper, Products>
    implements ProductsService{

}




