package wang.jilijili.framework.strategy.impl;

import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.core.pojo.entity.FileManage;
import wang.jilijili.common.core.service.impl.AsyncServerImpl;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.framework.config.UploadStoreProperties;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

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
    private AsyncServerImpl asyncServer;
    private final UploadStoreProperties uploadStoreProperties;


    @Autowired
    public void setAsyncServer(AsyncServerImpl asyncServer) {
        this.asyncServer = asyncServer;
    }

    @Autowired
    public LocalUploadStrategyImpl(UploadStoreProperties uploadStoreProperties) {
        this.uploadStoreProperties = uploadStoreProperties;

    }

    @Override
    public void initClient() {
        UploadStoreProperties.ConfigEntity local = uploadStoreProperties.getLocal();
        prefixUrl = local.getDomainUrl();


    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath) {
        String path = prefixUrl + fileRelativePath;
        File file = new File(path);
        return file.exists();

    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath) {


        File dest = checkFolderIsExisted(fileRelativePath);

        try {
            file.transferTo(dest);
            this.saveFileInfo(file, fileRelativePath);


        } catch (IOException e) {
            log.error(e.getMessage());
            throw new BizException(UPLOAD_FAILED);
        }
    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath) {
        UploadStoreProperties.ConfigEntity local = uploadStoreProperties.getLocal();

        return String.format("%s/%s/%s",
                local.getEndpoint(),
                local.getDomainUrl(),
                fileRelativePath);
    }

    @Override
    public void saveFileInfo(MultipartFile file, String fileRelativePath) {
        FileManage fileManage = new FileManage();
        fileManage.setFilesize(file.getSize());
        fileManage.setType(Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")));
        fileManage.setFilename(fileRelativePath);
        fileManage.setLocked(0);
        fileManage.setFilepath(getPublicNetworkAccessUrl(fileRelativePath));

        this.asyncServer.asyncSaveFile(fileManage);

    }


    /**
     * 检查文件夹是否存在，若不存在则创建文件夹，最终返回上传文件
     *
     * @param fileRelativePath 文件相对路径
     * @return {@link  File} 文件
     */
    private File checkFolderIsExisted(String fileRelativePath) {
        File rootPath = new File(prefixUrl + fileRelativePath);
        if (!rootPath.exists() && (!rootPath.mkdirs())) {
            throw new BizException(FILE_DIR_CREATED_FAILED);

        }
        return rootPath;
    }
}
