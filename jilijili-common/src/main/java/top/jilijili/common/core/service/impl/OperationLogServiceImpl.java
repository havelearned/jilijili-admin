package top.jilijili.common.core.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jilijili.common.core.mapper.OperationLogMapper;
import top.jilijili.common.core.pojo.bo.OperationLogConvertBo;
import top.jilijili.common.core.pojo.entity.OperationLog;
import top.jilijili.common.core.pojo.vo.OperationLogVO;
import top.jilijili.common.core.pojo.dto.OperationLogDto;
import top.jilijili.common.core.service.OperationLogService;

import java.util.List;

/**
 * @author admin
 * @description 针对表【operation_log】的数据库操作Service实现
 * @createDate 2023-03-06 10:26:13
 */
@Service
public class OperationLogServiceImpl extends ServiceImpl<OperationLogMapper, OperationLog>
        implements OperationLogService {

    OperationLogMapper operationLogMapper;
    OperationLogConvertBo operationLogConvertBo;

    @Autowired
    public OperationLogServiceImpl(OperationLogMapper operationLogMapper, OperationLogConvertBo operationLogConvertBo) {
        this.operationLogMapper = operationLogMapper;
        this.operationLogConvertBo = operationLogConvertBo;
    }

    @Override
    public List<OperationLogVO> list(IPage<OperationLogVO> page, OperationLogDto operationLogDto) {
        OperationLogVO operationLogVO = this.operationLogConvertBo.toVo(operationLogDto);
        QueryWrapper<OperationLogVO> queryWrapper = new QueryWrapper<>(operationLogVO);
        queryWrapper.orderByDesc("created_time");
        return this.operationLogMapper.pageList(page, queryWrapper);
    }
}




