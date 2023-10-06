package top.jilijili.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.CmRecord;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author admin
* @description 针对表【cm_record(多媒体聊天记录表)】的数据库操作Mapper
* @createDate 2023-08-05 21:32:58
* @Entity top.jilijili.module.entity.CmRecord
*/
@Mapper
public interface CmRecordMapper extends BaseMapper<CmRecord> {

}




