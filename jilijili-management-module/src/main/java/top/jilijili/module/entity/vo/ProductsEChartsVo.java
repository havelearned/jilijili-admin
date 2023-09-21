package top.jilijili.module.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Date;

/**
 * 订单 EChart 视图
 *
 * @author Amani
 * @date 2023年09月17日 22:57
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class ProductsEChartsVo {
    // 商品汇总字段
    /**
     * 时间
     */
    private Date createdTime;

    /**
     * 总上架数量
     */
    private Integer todayAddedTotal;

    /**
     * 商品数量
     */
    private Integer procedureCount;

}
