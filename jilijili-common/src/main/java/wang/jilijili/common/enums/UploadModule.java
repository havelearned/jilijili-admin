package wang.jilijili.common.enums;

import lombok.Getter;

/**
 * @author admin
 */

@Getter
public enum UploadModule {

    /**
     * 上传路径
     * 文件类型
     * 上传方式
     * */
    MUSIC_IMAGE_LOCAL("jilijili-music/","localUploadStrategyImpl"),

    /**
     * MPEG是动态图象专家组的英文缩写
     * 使用本地方式上传
     * */
    MUSIC_MPEG_LOCAL("jilijili-music/","localUploadStrategyImpl"),


    OSS_IMAGE_MUSIC("jilijili-music/","ossUploadStrategyImpl"),





    ;

    private final String path;

    private final String executedBeanName;

    UploadModule(String path,String executedName) {
        this.path = path;

        this.executedBeanName = executedName;
    }
}
