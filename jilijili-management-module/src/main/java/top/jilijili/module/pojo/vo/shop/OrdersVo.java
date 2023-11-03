package top.jilijili.module.pojo.vo.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.format.annotation.DateTimeFormat;
import top.jilijili.module.pojo.vo.sys.SysUserVo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 订单表
 *
 * @TableName shop_orders
 */

@Builder
@Accessors(chain = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrdersVo implements Serializable {


    /**
     * 订单ID
     */
    private String orderId;
    /**
     * 用户ID
     */
    private String userId;
    private String nickname;
    private String avatar;

    /**
     * 下单日期
     */
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+00:00")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private Date orderDate;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;




    private SysUserVo user;

    private List<OrdersItemVo> ordersItem;

}