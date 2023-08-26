package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import top.jilijili.system.entity.FileManagement;
import top.jilijili.system.service.FileManagementService;
import top.jilijili.system.mapper.FileManagementMapper;
import org.springframework.stereotype.Service;

/**
* @author admin
* @description 针对表【file_management(文件管理表)】的数据库操作Service实现
* @createDate 2023-07-11 13:33:01
*/
@Service
public class FileManagementServiceImpl extends ServiceImpl<FileManagementMapper, FileManagement>
    implements FileManagementService{

}




