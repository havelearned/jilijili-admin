package top.jilijili.mall.currency.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.pojo.entity.currency.TransactionHistory;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【shop_transaction_history】的数据库操作Mapper
* @createDate 2023-10-08 23:42:47
* @Entity top.jilijili.module.entity.TransactionHistory
*/
@Mapper
public interface TransactionHistoryMapper extends BaseMapper<TransactionHistory> {

}




