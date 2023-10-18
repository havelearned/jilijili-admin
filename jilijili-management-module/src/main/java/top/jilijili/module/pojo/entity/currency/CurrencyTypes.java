package top.jilijili.module.pojo.entity.currency;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * 货币类型
 * @TableName shop_currency_types
 */
@TableName(value = "shop_currency_types")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
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

    @TableField(value = "currency_code")
    private String currencyCode;

    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}