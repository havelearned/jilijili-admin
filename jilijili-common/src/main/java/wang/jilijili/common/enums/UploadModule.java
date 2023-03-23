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
    MUSIC_IMAGE_LOCAL("\\jilijili","\\image\\","localUploadStrategyImpl"),

    /**
     * MPEG是动态图象专家组的英文缩写
     * 使用本地方式上传
     * */
    MUSIC_MPEG_LOCAL("\\jilijili","\\MPEG\\","localUploadStrategyImpl"),



    /**
     * MP3也就是指的是MPEG标准中的音频部分
     * 使用本地方式上传
     * */
    MUSIC_MP3_LOCAL("\\jilijili","\\MP3\\","localUploadStrategyImpl"),


    /**
     * WMA 音质要强于MP3格式，更远胜于RA格式
     * 使用本地方式上传
     * */
    MUSIC_WMA_LOCAL("\\jilijili","\\WMA\\","localUploadStrategyImpl"),

    OSS_IMAGE_MUSIC("\\jilijili","IMAGE","ossUploadStrategyImpl"),





    ;

    private final String path;
    private final String type;
    private final String executedBeanName;

    UploadModule(String path, String type, String executedName) {
        this.path = path;
        this.type = type;
        this.executedBeanName = executedName;
    }
}
