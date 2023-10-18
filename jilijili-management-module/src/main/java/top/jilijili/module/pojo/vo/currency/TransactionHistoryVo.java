package top.jilijili.module.pojo.vo.currency;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.module.pojo.dto.SuperDto;
import top.jilijili.module.pojo.entity.currency.CurrencyTypes;
import top.jilijili.module.pojo.entity.sys.SysUser;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 交易历史
 *
 * @TableName shop_transaction_history
 */
@TableName(value = "shop_transaction_history")
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class TransactionHistoryVo extends SuperDto implements Serializable {
    /**
     * 交易历史id
     */
    private String transactionId;

    /**
     * 用户id
     */
//    private SysUser userId;
    private SysUser sysUser;

    /**
     * 货币类型id
     */
//    private String currencyTypeId;
    private CurrencyTypes currencyTypes;

    /**
     * 充值 购买商品 充值会员
     */
    private Integer transactionType;

    /**
     * 每次交易的金额
     */
    private BigDecimal amount;

    /**
     * 创建时间,交易日期
     */
    private Date createdTime;

    /**
     * 最后更新时间
     */
    private Date updatedTime;
}