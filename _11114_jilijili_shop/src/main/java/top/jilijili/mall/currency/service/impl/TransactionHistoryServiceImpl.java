package top.jilijili.mall.currency.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.module.entity.TransactionHistory;
import top.jilijili.mall.currency.service.TransactionHistoryService;
import top.jilijili.mall.currency.mapper.TransactionHistoryMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【shop_transaction_history】的数据库操作Service实现
* @createDate 2023-10-08 23:42:48
*/
@Service
public class TransactionHistoryServiceImpl extends ServiceImpl<TransactionHistoryMapper, TransactionHistory>
    implements TransactionHistoryService{

}




