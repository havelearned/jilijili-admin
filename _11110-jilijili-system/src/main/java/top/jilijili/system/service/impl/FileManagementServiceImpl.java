package top.jilijili.system.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.ListObjectsArgs;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.jilijili.system.common.config.MinioConfig;
import top.jilijili.system.common.utils.MinioUtil;
import top.jilijili.system.entity.FileItem;
import top.jilijili.system.entity.FileManagement;
import top.jilijili.system.entity.dto.FileManagementDto;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.heandler.JiliException;
import top.jilijili.system.mapper.FileManagementMapper;
import top.jilijili.system.service.FileManagementService;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 * @author admin
 * @description 针对表【file_management(文件管理表)】的数据库操作Service实现
 * @createDate 2023-07-11 13:33:01
 */
@Service
@AllArgsConstructor
public class FileManagementServiceImpl extends ServiceImpl<FileManagementMapper, FileManagement>
        implements FileManagementService {

    private MinioConfig minioConfig;


    /**
     * 获取文件列表
     *
     * @param fileManagementDto
     * @return
     */
    @Override
    public Result<Page<FileManagement>> getList(FileManagementDto fileManagementDto) {
        // 通过文件夹搜索
        if (StringUtils.hasText(fileManagementDto.getFilePath())) {
            String[] split = fileManagementDto.getFilePath().split("/");
            fileManagementDto.setFilePath(split[0] + "/");
        }
        Page<FileManagement> page = this.lambdaQuery()
                .eq(!Objects.isNull(fileManagementDto.getFileType()), FileManagement::getFileType, fileManagementDto.getFileType())
                .like(StringUtils.hasText(fileManagementDto.getFilePath()), FileManagement::getFilePath, fileManagementDto.getFilePath())
                .between(!Objects.isNull(fileManagementDto.getCreatedTime()), FileManagement::getCreatedTime, fileManagementDto.getCreatedTime(), fileManagementDto.getComparisonTime())
                .orderByDesc(FileManagement::getCreatedTime)
                .page(new Page<>(fileManagementDto.getPage(), fileManagementDto.getSize()));
        return Result.ok(page);
    }

    /**
     * 文件下载
     *
     * @param filePath 要下载的文件
     * @param expiry   有效期
     * @return 返回url
     */
    @Override
    public Result<String> fileDownload(String filePath, Integer expiry) {
        if (!StringUtils.hasText(filePath)) {
            throw new JiliException("操作失败,文件未找到");
        }
        String fileUrl = MinioUtil.getFileUrl(minioConfig.getBucket(), filePath, expiry);
        return Result.ok(fileUrl);

    }

    /**
     * 获取顶级目录列表
     */
    @Override
    public Result<List<FileItem>> getTopLevelDirectory() {
        Iterable<io.minio.Result<Item>> results = MinioConfig.minioClient.listObjects(
                ListObjectsArgs.builder()
                        .bucket(minioConfig.getBucket())
                        .prefix("/")
                        .build());
        Iterator<io.minio.Result<Item>> iterator = results.iterator();
        List<FileItem> resultItem = new ArrayList<>();
        try {
            while (iterator.hasNext()) {
                io.minio.Result<Item> next = iterator.next();
                Item item = next.get();
                FileItem fileItem = new FileItem();
                BeanUtils.copyProperties(item, fileItem);
                resultItem.add(fileItem);
            }
            return Result.ok(resultItem);
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.fail(e.getMessage());
        }
    }

    /**
     * 删除文件
     *
     * @param fileManagementDtoList
     * @return
     */
    @Override
    public Result<String> delFile(List<FileManagementDto> fileManagementDtoList) {
        if (fileManagementDtoList.isEmpty()) {
            throw new JiliException("操作失败");
        }
        List<DeleteObject> objectList = fileManagementDtoList.stream().map(item -> new DeleteObject(item.getFilePath())).toList();
        MinioUtil.removeFile(minioConfig.getBucket(), objectList);
        return Result.ok("操作成功");
    }

    /**
     * 添加文件目录
     * 例如: audio/
     * music/
     * office/
     * .....
     *
     * @param fileManagementDtoList 一个或者多个文件文件目录
     * @return
     */
    @Override
    public Result<String> addFileDir(List<FileManagementDto> fileManagementDtoList) {
        if (fileManagementDtoList.isEmpty()) {
            throw new JiliException("操作失败,目录为空");
        }
        for (FileManagementDto fileManagementDto : fileManagementDtoList) {
            MinioUtil.putFileDir(minioConfig.getBucket(), fileManagementDto.getFilePath());
        }
        return Result.ok("操作成功");
    }

}




