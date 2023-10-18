package top.jilijili.system.service.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.system.common.config.MinioConfig;
import top.jilijili.system.common.utils.FileType;
import top.jilijili.system.common.utils.MinioUtil;
import top.jilijili.module.pojo.entity.sys.FileItem;
import top.jilijili.module.pojo.entity.sys.FileManagement;
import top.jilijili.module.pojo.dto.sys.FileManagementDto;
import top.jilijili.module.pojo.vo.sys.FileManagementVo;
import top.jilijili.common.entity.Result;
import top.jilijili.common.heandler.JiliException;;
import top.jilijili.system.mapper.ConvertMapper;
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
@Slf4j
@Service
@AllArgsConstructor
public class FileManagementServiceImpl extends ServiceImpl<FileManagementMapper, FileManagement>
        implements FileManagementService {

    private MinioConfig minioConfig;

    private ConvertMapper convertMapper;

    /**
     * 文件上传
     *
     * @param files 一个或者多个文件
     * @return 一个或多个url
     */
    @Override
    public Result<Object> upload(MultipartFile[] files) {
        if (files.length < 1) {
            return Result.fail(403, "请选择文件后再上传");
        }
        StringBuilder builder = new StringBuilder(5);
        List<String> resulUrl = new ArrayList<>();
        try {
            for (MultipartFile file : files) {

                // 获取基本信息
                String endName = FileUtil.extName(file.getOriginalFilename());
                String prefix = FileType.assignFileTypes(endName);
                String md5Hex = DigestUtil.md5Hex(file.getInputStream());
                String filepath = builder.append(prefix).append("/").append(md5Hex).append(".").append(endName).toString();
                builder.setLength(0);

                // 文件上传
                MinioConfig.minioClient.putObject(PutObjectArgs
                        .builder()
                        .bucket(minioConfig.getBucket())
                        .object(filepath)
                        .stream(file.getInputStream(), file.getSize(), -1).build());

                // 获取文件url
                String url = MinioConfig.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(minioConfig.getBucket()).method(Method.GET).object(filepath).build());
                resulUrl.add(url);

                // 持久化到数据库
                this.save(FileManagement.builder()
                        .fileName(md5Hex + "." + endName)
                        .size(file.getSize())
                        .filePath(filepath).build());
            }
        } catch (Exception e) {
            throw new JiliException("文件上传失败");
        }
        return Result.ok(resulUrl);
    }

    /**
     * 获取文件列表
     *
     * @param fileManagementDto
     * @return
     */
    @Override
    public Result<IPage<FileManagementVo>> getList(FileManagementDto fileManagementDto) {
        // 通过文件夹搜索
        if (StringUtils.hasText(fileManagementDto.getFilePath())) {
            String[] split = fileManagementDto.getFilePath().split("/");
            fileManagementDto.setFilePath(split[0] + "/");
        }
        Page<FileManagement> page = this.lambdaQuery()
                .eq(!Objects.isNull(fileManagementDto.getFileType()), FileManagement::getFileType, fileManagementDto.getFileType())
                .like(StringUtils.hasText(fileManagementDto.getFileName()), FileManagement::getFileName, fileManagementDto.getFileName())
                .like(StringUtils.hasText(fileManagementDto.getFilePath()), FileManagement::getFilePath, fileManagementDto.getFilePath())
                .between(!Objects.isNull(fileManagementDto.getCreatedTime()), FileManagement::getCreatedTime, fileManagementDto.getCreatedTime(), fileManagementDto.getComparisonTime())
                .orderByDesc(FileManagement::getCreatedTime)
                .page(new Page<>(fileManagementDto.getPage(), fileManagementDto.getSize()));


        return Result.ok(page.convert(this.convertMapper::toFileManagementVo));
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
    @Transactional(rollbackFor = JiliException.class, propagation = Propagation.REQUIRES_NEW)
    @Override
    public Result<String> delFile(List<FileManagementDto> fileManagementDtoList) {
        if (fileManagementDtoList.isEmpty()) {
            throw new JiliException("操作失败");
        }

        // 同步数据库
        boolean b = this.removeBatchByIds(fileManagementDtoList);
        log.error("删除文件从成功? =>{}", b);
        if (!b) {
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




