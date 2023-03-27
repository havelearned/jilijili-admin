package wang.jilijili.web.common;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.enums.UploadModule;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.framework.strategy.UploadStrategyContext;

/**
 * 文件上传控制器
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

    @PostMapping("/local/image")
    public String localUpload(MultipartFile file) {
        if (file == null) {
            throw new BizException(ExceptionType.BAD_REQUEST);
        }
        return this.uploadStrategyContext
                .executeUploadStrategy(
                        file,
                        UploadModule.MUSIC_MPEG_LOCAL.getPath() + UploadModule.MUSIC_IMAGE_LOCAL.getType(),
                        UploadModule.MUSIC_MPEG_LOCAL.getExecutedBeanName());
    }

    @PostMapping("/oss/image")
    public String ossUpload(@RequestParam("file") MultipartFile file) {
        if (file == null) {
            throw new BizException(ExceptionType.BAD_REQUEST);
        }
        return this.uploadStrategyContext.executeUploadStrategy(
                file,
                UploadModule.OSS_IMAGE_MUSIC.getType(),
                UploadModule.OSS_IMAGE_MUSIC.getExecutedBeanName());
    }


}
