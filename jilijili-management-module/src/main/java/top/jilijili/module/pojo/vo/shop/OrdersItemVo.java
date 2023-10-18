package top.jilijili.module.pojo.vo.shop;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.jilijili.module.pojo.entity.shop.Categories;

import java.math.BigDecimal;

/**
 * @author Amani
 * @date 2023年10月05日 9:20
 */
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
@Data
public class OrdersItemVo {


    private ProductsVo products;
    private Categories categories;

    private Long itemId;
    private Integer quantity;
    private BigDecimal subtotal;
}
