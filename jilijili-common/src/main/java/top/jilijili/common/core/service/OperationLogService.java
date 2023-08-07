package top.jilijili.common.core.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.common.core.pojo.entity.OperationLog;
import top.jilijili.common.core.pojo.vo.OperationLogVO;
import top.jilijili.common.core.pojo.dto.OperationLogDto;

import java.util.List;

/**
* @author admin
* @description 针对表【operation_log】的数据库操作Service
* @createDate 2023-03-06 10:26:13
*/
public interface OperationLogService extends IService<OperationLog> {

    /**
     * 分页查询
     * @param page 分页
     * @param operationLogDto dto
     * @return vo
     */
    List<OperationLogVO> list(IPage<OperationLogVO> page, OperationLogDto operationLogDto);
}
