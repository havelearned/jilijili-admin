package top.jilijili.system.controller;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.aspose.pdf.SaveFormat;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.minio.GetPresignedObjectUrlArgs;
import io.minio.PutObjectArgs;
import io.minio.http.Method;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import top.jilijili.system.common.config.MinioConfig;
import top.jilijili.system.common.utils.FileType;
import top.jilijili.system.common.utils.MinioUtil;
import top.jilijili.system.entity.FileItem;
import top.jilijili.system.entity.FileManagement;
import top.jilijili.system.entity.dto.FileManagementDto;
import top.jilijili.system.entity.vo.Result;
import top.jilijili.system.service.FileManagementService;
import top.jilijili.system.service.group.Query;

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
public class FileUploadController extends SuperController {

    private MinioConfig minioConfig;
    private FileManagementService fileManagementService;


    /*========================================文件列表=========================================*/

    /**
     * 文件下载
     *
     * @param filePath 要下载的文件
     * @param expiry   有效期,默认7天,设置的有效期必须大于等于7天,否则返回的url无效
     * @return 返回url
     */
    @GetMapping("/download/{filePath}/{expiry}")
    public Result<String> fileDownload(@PathVariable String filePath,
                                       @PathVariable(required = false, name = "expiry") Integer expiry) {
        return this.fileManagementService.fileDownload(filePath, expiry);

    }


    /**
     * 删除文件
     *
     * @param fileManagementDtoList
     * @return
     */
    @DeleteMapping("/delFile")
    public Result<String> delFile(@RequestBody List<FileManagementDto> fileManagementDtoList) {
        return this.fileManagementService.delFile(fileManagementDtoList);
    }

    /**
     * 添加文件目录
     *
     * @param fileManagementDtoList 一个或者多个文件文件目录
     * @return
     */
    @PostMapping("/addFileDir")
    public Result<String> addFile(@RequestBody List<FileManagementDto> fileManagementDtoList) {
        return this.fileManagementService.addFileDir(fileManagementDtoList);
    }

    /**
     * 获取顶级目录列表
     */
    @GetMapping("/getTopLevelDir")
    public Result<List<FileItem>> getTopLevelDirectory() {
        return this.fileManagementService.getTopLevelDirectory();
    }

    /**
     * 获取所有文件列表
     *
     * @param fileManagementDto 文件查询参数
     * @return
     */
    @GetMapping("/list")
    public Result<Page<FileManagement>> list(@Validated(value = {Query.class})
                                             FileManagementDto fileManagementDto) {
        return this.fileManagementService.getList(fileManagementDto);
    }

    /*========================================为文件上传=========================================*/

    /**
     * PDF转其他文件
     *
     * @param type
     * @param file
     * @param response
     * @throws Exception
     */
    @PostMapping("/pdf/convert/{type}")
    public void PdfConvert(@PathVariable Integer type,
                           @RequestPart MultipartFile file,
                           HttpServletResponse response) throws Exception {
        switch (type) {
            case 1 -> {
                long old = System.currentTimeMillis();
                com.aspose.words.Document doc = new com.aspose.words.Document(file.getInputStream());
                doc.save(response.getOutputStream(), SaveFormat.Pdf);
                response.getOutputStream().flush();
                long now = System.currentTimeMillis();
                log.info("Word 转 Pdf 共耗时：{}{}", ((now - old) / 1000.0), "秒");
                super.setFileResponse(response, old + ".pdf");
            }
            default -> {
                long old = System.currentTimeMillis();
            }
        }

    }


    /**
     * 文件上传
     *
     * @param files
     * @return
     */
    @PostMapping("/upload")
    public Result<Object> upload(@RequestPart MultipartFile[] files) {
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
                MinioConfig.minioClient.putObject(PutObjectArgs
                        .builder()
                        .bucket(minioConfig.getBucket())
                        .object(filepath)
                        .stream(file.getInputStream(), file.getSize(), -1).build());

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
