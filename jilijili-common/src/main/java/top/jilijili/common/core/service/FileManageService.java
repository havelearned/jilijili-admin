package top.jilijili.common.core.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.common.core.pojo.dto.FileManageDto;
import top.jilijili.common.core.pojo.entity.FileManage;
import top.jilijili.common.core.pojo.vo.FileTypeTreeVO;

/**
 * @author admin
 * @description 针对表【file_manage】的数据库操作Service
 * @createDate 2023-03-23 09:30:14
 */

public interface FileManageService extends IService<FileManage> {

    /**
     * 分页查询
     *
     * @param iPage 分页
     * @param fileManageDto 查询实体
     * @return com.baomidou.mybatisplus.core.metadata.IPage<wang.jilijili.common.core.pojo.dto.FileManageDto>
     * @author Amani
     * @date 2023/4/4 10:53
     */
    IPage<FileManage> list(IPage<FileManage> iPage, FileManageDto fileManageDto);



    /**
     * 通过字典类型查询
     *
     * @param dictType
     * @return 树形结构
     */
    FileTypeTreeVO queryByDict(String dictType);
}
