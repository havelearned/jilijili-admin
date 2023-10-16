package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 
 * @TableName shop_virtual_currency
 */
@TableName(value ="shop_virtual_currency")
@Data
public class VirtualCurrency implements Serializable {
    /**
     * 货币id
     */
    @TableId(value = "currency_id", type = IdType.AUTO)
    private String currencyId;

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
     * 用户的虚拟货币余额
     */
    @TableField(value = "balance")
    private BigDecimal balance;

    /**
     * 创建时间
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