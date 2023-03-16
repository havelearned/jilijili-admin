package wang.jilijili.framework.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author Amani
 * @date 2023年03月16日 上午11:07
 */
@Data
@Component
@ConfigurationProperties(prefix = "upload.store")
public class UploadStoreProperties {

    private ConfigEntity local;
    private ConfigEntity oss;
    private ConfigEntity minio;


    @Data
    public static class ConfigEntity{
        /**
         * 访问域名
         */
        private String domainUrl;

        /**
         * key
         */
        private String accessKey;

        /**
         * 密钥
         */
        private String accessKeySecret;


        /**
         * 地域节点
         */
        private String endpoint;


        /**
         * 存储桶名称
         */
        private String bucket;
    }
}
