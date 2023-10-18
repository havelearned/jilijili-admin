package top.jilijili.module.pojo.dto.currency;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.module.pojo.dto.SuperDto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;


/**
 * 虚拟货币
 *
 * @TableName shop_virtual_currency
 */
@TableName(value = "shop_virtual_currency")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class VirtualCurrencyDto extends SuperDto implements Serializable {
    /**
     * 货币id
     */
    private String currencyId;

    /**
     * 用户id
     */
    private String userId;

    private String username;

    /**
     * 货币类型id
     */
    private String currencyName;


    /**
     * 用户的虚拟货币余额
     */
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后更新时间
     */
    private Date updatedTime;

    private Long min;

    private Long max;

}
