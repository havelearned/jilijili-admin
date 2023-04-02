package wang.jilijili.framework.strategy.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.framework.config.UploadStoreProperties;

import java.io.File;
import java.io.IOException;

import static wang.jilijili.common.exception.ExceptionType.FILE_DIR_CREATED_FAILED;
import static wang.jilijili.common.exception.ExceptionType.UPLOAD_FAILED;

/**
 * 本地上传
 *
 * @author Amani
 * @date 2023年03月16日 上午11:41
 */
@Slf4j
@Service
@Getter
public class LocalUploadStrategyImpl extends AbstractUploadStrategyImpl {
    private String prefixUrl;
    UploadStoreProperties uploadStoreProperties;

    public LocalUploadStrategyImpl(UploadStoreProperties uploadStoreProperties) {
        this.uploadStoreProperties = uploadStoreProperties;
    }

    @Override
    public void initClient() {

        prefixUrl = uploadStoreProperties.getLocal().getDomainUrl();

        log.info("localClient Init Success...");

    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath) {
        return new File(prefixUrl + fileRelativePath).exists();
    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath) {
        File dest = checkFolderIsExisted(fileRelativePath);
        try {
            file.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new BizException(UPLOAD_FAILED);
        }


    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath) {
        String fileUrl = uploadStoreProperties.getLocal().getEndpoint()
                + "/"
                + uploadStoreProperties.getLocal().getDomainUrl()
                + fileRelativePath;
        return fileUrl.replace("\\", "/");
    }


    /**
     * 检查文件夹是否存在，若不存在则创建文件夹，最终返回上传文件
     *
     * @param fileRelativePath 文件相对路径
     * @return {@link  File} 文件
     */
    private File checkFolderIsExisted(String fileRelativePath) {
        File rootPath = new File(prefixUrl + fileRelativePath);
        if (!rootPath.exists()) {
            if (!rootPath.mkdirs()) {
                throw new BizException(FILE_DIR_CREATED_FAILED);
            }
        }
        return rootPath;
    }
}
