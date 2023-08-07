package top.jilijili.system.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.ListObjectsArgs;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import io.minio.messages.Item;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.system.common.config.MinioConfig;
import top.jilijili.system.common.utils.FileType;
import top.jilijili.system.common.utils.MinioUtil;
import top.jilijili.system.entity.FileManagement;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.service.FileManagementService;

import java.util.ArrayList;
import java.util.List;

import static top.jilijili.system.common.utils.KeyConstants.SONG_DATA_IMPORT_TEMPLATE_LIST;


/**
 * 文件管理
 *
 * @author Amani
 * @date 2023年07月11日 12:01
 */
@Slf4j
@RestController
@RequestMapping("/file")
@AllArgsConstructor
public class FileUploadController {

    private MinioConfig minioConfig;
    private FileManagementService fileManagementService;

    /**
     * 获取所有文件列表
     *
     * @return
     */
    @GetMapping("/list")
    public Result<StringBuilder> list() {
        StringBuilder builder = new StringBuilder(20);
        try {
            for (io.minio.Result<Item> next : MinioConfig.minioClient.listObjects(ListObjectsArgs.builder().bucket(minioConfig.getBucket()).prefix("/OFFICE/").build())) {
                Item item = next.get();
                String objectUrl = MinioConfig.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(minioConfig.getBucket()).method(Method.GET).object(item.objectName()).build());
                builder.append(objectUrl);
            }
        } catch (Exception e) {
            log.error(e.getMessage());
        }

        return Result.ok(builder);

    }


    /**
     * 文件上传
     *
     * @param files
     * @return
     */
    @PostMapping("/upload")
    public Result<?> upload(@RequestPart MultipartFile[] files) {
        if (!(files.length >= 1)) {
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
                MinioConfig.minioClient.putObject(PutObjectArgs.builder().bucket(minioConfig.getBucket()).object(filepath).stream(file.getInputStream(), file.getSize(), -1).build());
                // 获取文件url
                String url = MinioConfig.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder().bucket(minioConfig.getBucket()).method(Method.GET).object(filepath).build());
                resulUrl.add(url);

                // 持久化到数据库
                fileManagementService.save(FileManagement.builder()
                        .fileName(md5Hex + "." + endName)
                        .filePath(filepath).build());
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return Result.fail(e.getMessage(), "文件上传失败");
        }
        return Result.ok(resulUrl);
    }

    /**
     * 得到所有下载模板文件 url
     *
     * @return
     */
    @GetMapping("getDownloadTemplateList")
    public List<String> getDownloadTemplateList() {
        List<String> list = new ArrayList<>();
        SONG_DATA_IMPORT_TEMPLATE_LIST.forEach(item -> {
            String fileUrl = MinioUtil.getFileUrl(item);
            list.add(fileUrl);
        });
        return list;
    }
}
