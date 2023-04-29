package wang.jilijili.common.enums;

import lombok.Getter;

/**
 * @author admin
 */

@Getter
public enum UploadModule {



    /**
     * MPEG是动态图象专家组的英文缩写
     * 使用本地方式上传
     * */
    MUSIC_MPEG_LOCAL("jilijili-music/","localUploadStrategyImpl"),


    OSS_IMAGE_MUSIC("jilijili-music/","ossUploadStrategyImpl"),

    MINIO_IMAGE_MUSIC("jilijili-music/","minioUploadStrategyImpl"),

    ;

    private final String path;

    private final String executedBeanName;

    UploadModule(String path,String executedName) {
        this.path = path;

        this.executedBeanName = executedName;
    }
}
