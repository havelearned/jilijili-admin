package top.jilijili.module.pojo.dto.currency;

import com.baomidou.mybatisplus.annotation.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.module.pojo.dto.SuperDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 货币类型
 * @TableName shop_currency_types
 */
@TableName(value = "shop_currency_types")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class CurrencyTypesDto extends SuperDto implements Serializable {
    /**
     * 货币类型id
     */
    private String currencyTypeId;

    /**
     * 货币类型名称，例如"b币"
     */
    private String currencyName;

    private String currencyCode;

    private Date createdTime;

    private Date updatedTime;

}