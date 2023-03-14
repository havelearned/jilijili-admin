package wang.jilijili.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import wang.jilijili.system.mapper.OperationLogMapper;
import wang.jilijili.system.pojo.entity.OperationLog;
import wang.jilijili.system.service.OperationLogService;

/**
* @author admin
* @description 针对表【operation_log】的数据库操作Service实现
* @createDate 2023-03-06 10:26:13
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
    implements OperationLogService {

}




