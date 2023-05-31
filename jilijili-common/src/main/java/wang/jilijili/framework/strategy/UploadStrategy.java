package wang.jilijili.framework.strategy;

import org.springframework.web.multipart.MultipartFile;

/**
 * @author admin
 */
public interface UploadStrategy {


    /**
     * 文件上传
     * @author Amani
     * @date 16/3/2023 上午11:02
     * @param file 文件对象
     * @param filePath 上传目录位置
     * @return java.lang.String 文件url
     */
    String uploadFile(final MultipartFile file, final String  filePath);
}
