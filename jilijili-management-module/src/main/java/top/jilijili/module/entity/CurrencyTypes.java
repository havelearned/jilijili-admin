package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @TableName shop_currency_types
 */
@TableName(value = "shop_currency_types")
@Data
public class CurrencyTypes implements Serializable {
    /**
     * 货币类型id
     */
    @TableId(value = "currency_type_id", type = IdType.AUTO)
    private String currencyTypeId;

    /**
     * 货币类型名称，例如"b币"
     */
    @TableField(value = "currency_name")
    private String currencyName;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}