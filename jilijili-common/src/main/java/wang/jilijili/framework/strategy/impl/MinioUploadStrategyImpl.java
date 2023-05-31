package wang.jilijili.framework.strategy.impl;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.core.pojo.entity.FileManage;
import wang.jilijili.common.core.service.impl.AsyncServerImpl;
import wang.jilijili.common.exception.BizException;
import wang.jilijili.common.exception.ExceptionType;
import wang.jilijili.framework.config.UploadStoreProperties;

import java.io.IOException;
import java.util.Objects;

/**
 * @author Amani
 * @date 2023年04月23日 12:47
 */
@Slf4j
@Service
public class MinioUploadStrategyImpl extends AbstractUploadStrategyImpl {
    UploadStoreProperties uploadStoreProperties;

    AsyncServerImpl asyncServer;
    MinioClient minioClient;

    @Autowired
    public void setAsyncServer(AsyncServerImpl asyncServer) {
        this.asyncServer = asyncServer;
    }


    public MinioUploadStrategyImpl(UploadStoreProperties uploadStoreProperties) {
        this.uploadStoreProperties = uploadStoreProperties;

    }

    @Override
    public void initClient() {
        UploadStoreProperties.ConfigEntity minio = this.uploadStoreProperties.getMinio();
        try {
            this.minioClient = MinioClient.builder()
                    .endpoint(minio.getDomainUrl())
                    .credentials(minio.getAccessKey(), minio.getAccessKeySecret())
                    .build();
            if (!this.minioClient.bucketExists(
                    BucketExistsArgs.builder().bucket(minio.getBucket()).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(minio.getBucket())
                        .build());
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ExceptionType.OSS_INIT_FAIL);
        }

    }

    @Override
    public boolean checkFileIsExisted(String fileRelativePath) {
        return false;
    }

    @Override
    public void executeUpload(MultipartFile file, String fileRelativePath) throws IOException {
        UploadStoreProperties.ConfigEntity minio = this.uploadStoreProperties.getMinio();
        try {
            this.minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(minio.getBucket())
                            .object(fileRelativePath)
                            .stream(file.getInputStream(), file.getSize(), -1)
                            .contentType(file.getContentType())
                            .build());

            this.saveFileInfo(file, fileRelativePath);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(ExceptionType.OSS_INIT_FAIL);
        }
    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath) {
        return String.format("%s/%s/%s",
                this.uploadStoreProperties.getMinio().getDomainUrl(),
                this.uploadStoreProperties.getMinio().getBucket(),
                fileRelativePath);

    }

    @Override
    public void saveFileInfo(MultipartFile file, String fileRelativePath) {
        FileManage fileManage = new FileManage();
        fileManage.setFilesize(file.getSize());
        fileManage.setType(Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".")));
        fileManage.setFilename(file.getOriginalFilename());
        fileManage.setLocked(0);
        fileManage.setFilepath(getPublicNetworkAccessUrl(fileRelativePath));

        this.asyncServer.asyncSaveFile(fileManage);
    }
}
