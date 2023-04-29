package wang.jilijili.web.common;

import cn.hutool.core.io.FileUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.core.pojo.vo.Result;
import wang.jilijili.common.enums.UploadModule;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.common.utils.FileType;
import wang.jilijili.framework.strategy.UploadStrategyContext;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static wang.jilijili.common.utils.FileType.OTHER;

/**
 * 文件上传管理
 *
 * @author Amani
 * @date 2023年03月16日 下午12:47
 */

@RestController
@RequestMapping("/upload")
public class UploadController {

    UploadStrategyContext uploadStrategyContext;


    public UploadController(UploadStrategyContext uploadStrategyContext) {
        this.uploadStrategyContext = uploadStrategyContext;
    }

    /**
     * minio批量上传文件
     *
     * @param files 多个文件
     * @return 返回文件连接列表
     */
    @PostMapping("/multiple/minio")
    public Result<List<String>> multipleMinioUploadImage(@RequestPart(value = "files") final MultipartFile[] files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String type = getFileTypeSaveDir(file);
            String url = this.uploadStrategyContext.executeUploadStrategy(
                    file,
                    UploadModule.MINIO_IMAGE_MUSIC.getPath() + type,
                    UploadModule.MINIO_IMAGE_MUSIC.getExecutedBeanName());
            urls.add(url);
        }
        return Result.ok(urls);
    }

    /**
     * 本地上传
     *
     * @param file 文件实体
     * @return 返回文件连接
     */
    @PostMapping("/local/image")
    public Result<String> localUploadImage(MultipartFile file) {
        String type = getFileTypeSaveDir(file);
        String urlPath = this.uploadStrategyContext.executeUploadStrategy(
                file,
                UploadModule.OSS_IMAGE_MUSIC.getPath() + type,
                UploadModule.MUSIC_MPEG_LOCAL.getExecutedBeanName());
        return Result.ok(urlPath);
    }


    /**
     * local批量上传文件
     *
     * @param files 多个文件
     * @return 返回文件连接列表
     */
    @PostMapping("/multiple/local/image")
    public Result<List<String>> multipleLocalUploadImage(@RequestPart(value = "files") final MultipartFile[] files) {

        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String type = getFileTypeSaveDir(file);
            String url = this.uploadStrategyContext.executeUploadStrategy(
                    file,
                    UploadModule.MUSIC_MPEG_LOCAL.getPath() + type,
                    UploadModule.MUSIC_MPEG_LOCAL.getExecutedBeanName());
            urls.add(url);
        }
        return Result.ok(urls);
    }

    /**
     * oss文件上传
     *
     * @param file 文件实体
     * @return 返回文件连接
     */
    @PostMapping("/oss/image")
    public Result<String> ossUploadImage(@RequestPart(value = "file") final MultipartFile file) {
        String type = getFileTypeSaveDir(file);
        String urlPath = this.uploadStrategyContext.executeUploadStrategy(
                file,
                UploadModule.OSS_IMAGE_MUSIC.getPath() + type,
                UploadModule.OSS_IMAGE_MUSIC.getExecutedBeanName());
        return Result.ok(urlPath);
    }


    /**
     * 保存到对应目录下
     *
     * @param file 上传的文件实体
     * @return java.lang.String 返回文件连接|文件路径包含后缀
     * @author Amani
     * @date 27/3/2023 下午7:03
     */
    private static String getFileTypeSaveDir(MultipartFile file) {
        if (file == null) {
            throw new BizException(ExceptionType.BAD_REQUEST);
        }

        // 得到文件后缀
        String suffix = FileUtil.getSuffix(file.getOriginalFilename());

        Iterator<Map.Entry<String, String[]>> iterator =
                FileType.ASSORT_EXTENSION.entrySet().iterator();

        String type = OTHER;
        while (iterator.hasNext()) {
            Map.Entry<String, String[]> next = iterator.next();
            String[] value = next.getValue();
            for (String s : value) {
                if (s.contains(suffix.toLowerCase())) {
                    type = next.getKey();
                    break;
                }
            }
        }
        return type;
    }

    /**
     * oss批量上传文件
     *
     * @param files 多个文件
     * @return 返回文件连接列表
     */
    @PostMapping("/multiple/oss/image")
    public Result<List<String>> ossUploadMultipleImage(@RequestPart("files") final MultipartFile[] files) {
        List<String> urls = new ArrayList<>();
        for (MultipartFile file : files) {
            String type = getFileTypeSaveDir(file);
            String url = this.uploadStrategyContext.executeUploadStrategy(
                    file,
                    UploadModule.OSS_IMAGE_MUSIC.getPath() + type,
                    UploadModule.OSS_IMAGE_MUSIC.getExecutedBeanName());
            urls.add(url);
        }
        return Result.ok(urls);
    }


}
