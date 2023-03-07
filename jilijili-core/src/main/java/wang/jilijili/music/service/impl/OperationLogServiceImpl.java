package wang.jilijili.music.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import wang.jilijili.music.pojo.entity.OperationLog;
import wang.jilijili.music.service.OperationLogService;
import wang.jilijili.music.mapper.OperationLogMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【operation_log】的数据库操作Service实现
* @createDate 2023-03-06 10:26:13
*/
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
    implements OperationLogService{

}




