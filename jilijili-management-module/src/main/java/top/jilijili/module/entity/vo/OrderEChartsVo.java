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
public class OrderEChartsVo {
    // 订单汇总字段
    /**
     * 时间
     */
    private Date createdTime;
    /**
     * 完成订单数量
     */
    private Integer completedOrders;
    /**
     * 未完成订单数量
     */
    private Integer incompleteOrders;
    /**
     * 过期的订单数量
     */
    private Integer expiredOrders;

    /**
     * 订单总数量
     */
    private Integer totalOrder;
}
