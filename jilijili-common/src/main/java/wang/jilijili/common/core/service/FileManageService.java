package wang.jilijili.common.core.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import wang.jilijili.common.core.pojo.entity.FileManage;
import com.baomidou.mybatisplus.extension.service.IService;

/**
* @author admin
* @description 针对表【file_manage】的数据库操作Service
* @createDate 2023-03-23 09:30:14
*/
@DS("master")
public interface FileManageService extends IService<FileManage> {

}
