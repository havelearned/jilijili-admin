package wang.jilijili.common.core.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import wang.jilijili.common.core.pojo.entity.FileManage;

/**
 * @author admin
 * @description 针对表【file_manage】的数据库操作Mapper
 * @createDate 2023-03-23 09:30:14
 * @Entity wang.jilijili.common.core.pojo.entity.FileManage
 */
@Mapper
public interface FileManageMapper extends BaseMapper<FileManage> {

}




