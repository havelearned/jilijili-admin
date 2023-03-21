package wang.jilijili.common.core.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wang.jilijili.common.core.pojo.entity.OperationLog;

/**
 * @author admin
 * @description 针对表【operation_log】的数据库操作Mapper
 * @createDate 2023-03-06 10:26:13
 * @Entity wang.jilijili.music.pojo.entity.OperationLog
 */
@Mapper
public interface OperationLogMapper extends BaseMapper<OperationLog> {

}




