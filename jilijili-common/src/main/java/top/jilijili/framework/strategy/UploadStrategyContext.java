package top.jilijili.framework.strategy;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.util.Map;

import static top.jilijili.common.enums.UploadModule.getStrategy;

/**
 * @author Amani
 * @date 2023年03月16日 下午12:44
 */
@Component
@RequiredArgsConstructor
public class UploadStrategyContext {

    @Value("${upload.strategy}")
    private String uploadStrategy;
    private Map<String, UploadStrategy> uploadStrategyMap;

    @Autowired
    public void setUploadStrategyMap(Map<String, UploadStrategy> uploadStrategyMap) {
        this.uploadStrategyMap = uploadStrategyMap;
    }

    /**
     * 执行上传策略
     *
     * @param file     文件
     * @param filePath 文件上传路径前缀
     * @return {@link String} 文件上传全路径
     */
    public String executeUploadStrategy(MultipartFile file, final String filePath, String uploadServiceName) {
        UploadStrategy uploadStrategy = this.uploadStrategyMap.get(uploadServiceName);
        return uploadStrategy.uploadFile(file, filePath);

    }

    /**
     * 执行上传策略
     *
     * @param file     文件
     * @param filePath 文件上传路径前缀
     * @return {@link String} 文件上传全路径
     */
    public String executeUploadStrategy(MultipartFile file, final String filePath) {
        UploadStrategy uploadStrategy = this.uploadStrategyMap.get(getStrategy(this.uploadStrategy));
        return uploadStrategy.uploadFile(file, filePath);

    }
}
