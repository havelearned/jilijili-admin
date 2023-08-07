package top.jilijili.common.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author admin
 */

@Getter
@AllArgsConstructor
public enum UploadModule {


    /**
     * MPEG是动态图象专家组的英文缩写
     * 使用本地方式上传
     */
    MUSIC_MPEG_LOCAL("local", "localUploadStrategyImpl"),


    OSS_IMAGE_MUSIC("oss", "ossUploadStrategyImpl"),

    MINIO_IMAGE_MUSIC("minio", "minioUploadStrategyImpl");

    private final String path;

    private final String executedBeanName;


    public static String getStrategy(String mode) {
        for (UploadModule value : UploadModule.values()) {
            if (value.getPath().equals(mode)) {
                return value.getExecutedBeanName();
            }
        }
        return null;
    }
}
