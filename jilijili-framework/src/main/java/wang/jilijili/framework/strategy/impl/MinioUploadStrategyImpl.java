package wang.jilijili.framework.strategy.impl;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import wang.jilijili.common.core.service.FileManageService;
import wang.jilijili.framework.config.UploadStoreProperties;

import java.io.IOException;

/**
 * @author Amani
 * @date 2023年04月23日 12:47
 */
@Slf4j
@Service
public class MinioUploadStrategyImpl extends AbstractUploadStrategyImpl {

    UploadStoreProperties uploadStoreProperties;

    FileManageService fileManageService;

    MinioClient minioClient;


    public MinioUploadStrategyImpl(UploadStoreProperties uploadStoreProperties, FileManageService fileManageService) {
        this.uploadStoreProperties = uploadStoreProperties;
        this.fileManageService = fileManageService;
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
            log.error(e.getMessage());
            e.printStackTrace();
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
        } catch (Exception e) {
            log.info(e.getMessage());
            e.printStackTrace();
        }
    }

    @Override
    public String getPublicNetworkAccessUrl(String fileRelativePath) {
        return String.format("%s/%s/%s",
                this.uploadStoreProperties.getMinio().getDomainUrl(),
                this.uploadStoreProperties.getMinio().getBucket(),
                fileRelativePath);

    }
}
