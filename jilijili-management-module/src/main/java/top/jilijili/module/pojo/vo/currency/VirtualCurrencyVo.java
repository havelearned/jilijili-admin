package top.jilijili.module.pojo.vo.currency;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import top.jilijili.common.utils.BigDecimalSerialize;
import top.jilijili.module.pojo.entity.currency.CurrencyTypes;
import top.jilijili.module.pojo.vo.sys.SysUserVo;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class VirtualCurrencyVo implements Serializable {
    /**
     * 货币id
     */
    private String currencyId;

    /**
     * 用户id
     */
//    private String userId;
    private SysUserVo sysUser;

    /**
     * 货币类型id
     */
//    private String currencyTypeId;
    private CurrencyTypes currencyTypes;


    /**
     * 用户的虚拟货币余额
     */
    @JsonSerialize(using = BigDecimalSerialize.class)
    private BigDecimal balance;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 最后更新时间
     */
    private Date updatedTime;

}
