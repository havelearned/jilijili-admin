package wang.jilijili.common.core.mapper;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.Mapper;
import wang.jilijili.common.core.pojo.entity.OperationLog;
import wang.jilijili.common.core.pojo.vo.OperationLogVO;

import java.util.List;

/**
 * @author admin
 * @description 针对表【operation_log】的数据库操作Mapper
 * @createDate 2023-03-06 10:26:13
 * @Entity wang.jilijili.music.pojo.entity.OperationLog
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

    /**
     * 分页查询
     *
     * @param page         分页
     * @param queryWrapper 查询条件
     * @return vo
     */
    List<OperationLogVO> pageList(IPage<OperationLogVO> page, QueryWrapper<OperationLog> queryWrapper);
}




