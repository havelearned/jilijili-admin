package top.jilijili.system.common.utils;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.http.Method;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import top.jilijili.system.common.config.MinioConfig;

import java.util.concurrent.TimeUnit;

/**
 * @author Amani
 * @date 2023年07月13日 16:30
 */

@Component
@Slf4j
public class MinioUtil {


    private static MinioConfig minioConfig;

    @Autowired
    public void setMinioConfig(MinioConfig minioConfig) {
        MinioUtil.minioConfig = minioConfig;
    }

    /**
     * 获取文件 url
     *
     * @param path 文件路径,例如: /Excel/歌手数据导入模板.xlsx
     * @return
     */
    public static String getFileUrl(String path) {
        try {
            return MinioConfig.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .method(Method.GET)
                    .object(path)
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            return "null";
        }
    }


    /**
     * 获取文件 url
     *
     * @param path   路径
     * @param expiry 过期时间,秒单位
     * @return
     */
    public static String getFileUrl(String path, int expiry) {
        try {
            return MinioConfig.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .method(Method.GET)
                    .object(path)
                    .expiry(expiry, TimeUnit.SECONDS)
                    .build());
        } catch (Exception e) {
            log.error(e.getMessage());
            return "null";
        }
    }

}
