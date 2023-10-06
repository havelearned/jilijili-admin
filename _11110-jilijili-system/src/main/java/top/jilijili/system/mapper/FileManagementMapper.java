package top.jilijili.system.mapper;

import org.apache.ibatis.annotations.Mapper;
import top.jilijili.module.entity.FileManagement;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
 * @author admin
 * @description 针对表【file_management(文件管理表)】的数据库操作Mapper
 * @createDate 2023-07-11 13:33:01
 * @Entity top.jilijili.module.entity.FileManagement
 */
@Mapper
public interface FileManagementMapper extends BaseMapper<FileManagement> {

}




