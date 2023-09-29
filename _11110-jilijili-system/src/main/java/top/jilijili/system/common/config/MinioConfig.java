package top.jilijili.system.common.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.Data;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Amani
 * @date 2023年07月11日 11:21
 */
@Data
@Slf4j
@Getter
@Component
@ConfigurationProperties(prefix = "minio")
public class MinioConfig implements InitializingBean {

    private String domainUrl;
    private String accessKey;
    private String accessKeySecret;
    private String bucket;
    public static MinioClient minioClient;


    /**
     * Spring初始化完成后对这个Bean进行后置处理
     * 后置处理
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        initMainioClient();
        log.info("Minio Client =>初始化成功");
    }

    public void initMainioClient() {
        try {
            minioClient = MinioClient.builder().endpoint(this.getDomainUrl())
                    .credentials(this.getAccessKey(), this.getAccessKeySecret())
                    .build();

            if (!minioClient.bucketExists(BucketExistsArgs.builder().bucket(this.getBucket()).build())) {
                minioClient.makeBucket(MakeBucketArgs.builder()
                        .bucket(this.getBucket())
                        .build());
            }
        } catch (Exception e) {
            log.error("minio初始化失败,错误信息:{}",e.getMessage());
        }

    }
}
