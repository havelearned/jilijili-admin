package top.jilijili.module.pojo.entity.currency;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易历史
 * @TableName shop_transaction_history
 */
@TableName(value ="shop_transaction_history")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TransactionHistory implements Serializable {
    /**
     * 交易历史id
     */
    @TableId(value = "transaction_id", type = IdType.AUTO)
    private String transactionId;

    /**
     * 用户id
     */
    @TableField(value = "user_id")
    private String userId;

    /**
     * 货币类型id
     */
    @TableField(value = "currency_type_id")
    private String currencyTypeId;

    /**
     * 充值 购买商品 充值会员
     */
    @TableField(value = "transaction_type")
    private Integer transactionType;

    /**
     * 每次交易的金额
     */
    @TableField(value = "amount")
    private BigDecimal amount;

    /**
     * 创建时间,交易日期
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     * 最后更新时间
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}