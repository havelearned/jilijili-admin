package top.jilijili.module.pojo.dto.shop;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.springframework.format.annotation.DateTimeFormat;
import top.jilijili.common.entity.SuperDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单表
 *
 * @TableName shop_orders
 */
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@Data
public class OrdersDto extends SuperDto implements Serializable {
    /**
     * 订单ID
     */
    private Long orderId;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 下单日期
     */
    private Date orderDate;
    @JsonFormat(pattern = "YYYY-MM-DD HH:mm")
    @DateTimeFormat(pattern = "YYYY-MM-DD HH:mm")
    private String orderDateFormat;

    /**
     * 订单状态
     */
    private Integer orderStatus;

    /**
     * 总金额
     */
    private BigDecimal totalAmount;
    private BigDecimal min;
    private BigDecimal max;

    /**
     * 创建时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdTime;

    /**
     * 更新时间
     */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedTime;

}