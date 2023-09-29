package top.jilijili.system.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import top.jilijili.system.entity.FileItem;
import top.jilijili.system.entity.FileManagement;
import top.jilijili.system.entity.dto.FileManagementDto;
import top.jilijili.system.entity.vo.Result;

import java.util.List;

/**
 * @author admin
 * @description 针对表【file_management(文件管理表)】的数据库操作Service
 * @createDate 2023-07-11 13:33:01
 */
public interface FileManagementService extends IService<FileManagement> {

    /**
     * 获取文件列表
     *
     * @param fileManagementDto
     * @return
     */
    Result<Page<FileManagement>> getList(FileManagementDto fileManagementDto);

    /**
     * 获取顶级目录列表
     */
    Result<List<FileItem>> getTopLevelDirectory();


    /**
     * 添加文件目录
     *
     * @param fileManagementDtoList 一个或者多个文件文件目录
     * @return
     */
    Result<String> addFileDir(List<FileManagementDto> fileManagementDtoList);

    /**
     * 删除文件
     *
     * @param fileManagementDtoList
     * @return
     */
    Result<String> delFile(List<FileManagementDto> fileManagementDtoList);

    /**
     * 文件下载
     *
     * @param filePath 要下载的文件
     * @param expiry   有效期
     * @return 返回url
     */
    Result<String> fileDownload(String filePath, Integer expiry);
}
