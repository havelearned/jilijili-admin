package top.jilijili.system.common.utils;

import io.minio.GetPresignedObjectUrlArgs;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectsArgs;
import io.minio.StatObjectArgs;
import io.minio.http.Method;
import io.minio.messages.DeleteObject;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import top.jilijili.system.common.config.MinioConfig;
import top.jilijili.common.heandler.JiliException;;

import java.io.ByteArrayInputStream;
import java.util.List;

/**
 * @author Amani
 * @date 2023年07月13日 16:30
 */

@Component
@Slf4j
public class MinioUtil {


    private static MinioConfig minioConfig;

    @Resource
    public void setMinioConfig(MinioConfig minioConfig) {
        MinioUtil.minioConfig = minioConfig;
    }


    /**
     * 添加一个目录
     *
     * @param bucket
     * @param filePath
     * @return
     */
    public static boolean putFileDir(String bucket, String filePath) {
        try {
            MinioConfig.minioClient.putObject(
                    PutObjectArgs.builder()
                            .bucket(bucket)
                            .object(filePath)
                            .stream(new ByteArrayInputStream(new byte[]{}), 0, -1)
                            .build());
            return true;
        } catch (Exception e) {
            throw new JiliException("操作失败,目录为空");
        }
    }

    /**
     * 获取文件 url
     *
     * @param path 文件路径,例如: /Excel/歌手数据导入模板.xlsx
     * @return
     * @Deprecated 请使用其他的重载函数
     */
    @Deprecated
    public static String getFileUrl(String path) {
        MinioUtil.fileExists(minioConfig.getBucket(), path);
        try {
            return MinioConfig.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(minioConfig.getBucket())
                    .method(Method.GET)
                    .object(path)
                    .build());
        } catch (Exception e) {
            throw new JiliException("获取文件资源(url)错误!!!");
        }
    }


    /**
     * 获取文件 url
     *
     * @param path   路径
     * @param expiry 过期时间,秒单位
     * @return
     */
    public static String getFileUrl(String bucket, String path, int expiry) {
        MinioUtil.fileExists(minioConfig.getBucket(), path);
        try {
            return MinioConfig.minioClient.getPresignedObjectUrl(GetPresignedObjectUrlArgs.builder()
                    .bucket(bucket)
                    .method(Method.GET)
                    .object(path)
                    .expiry(expiry)
                    .build());
        } catch (Exception e) {
            throw new JiliException(e.getMessage());
        }
    }

    /**
     * 检测文件是否存在
     *
     * @param bucket 桶
     * @param path   路径
     * @return 是否存在
     */
    public static boolean fileExists(String bucket, String path) {
        try {
            MinioConfig.minioClient.statObject(StatObjectArgs.builder()
                    .bucket(bucket)
                    .object(path)
                    .build());
            return true;
        } catch (Exception e) {
            throw new JiliException(e.getMessage());
        }
    }

    /**
     * 批量删除一个或者多个文件
     * minio 目录下没有文件时会自动删除目录
     *
     * @param bucket     同
     * @param objectList 要删除的文件信息
     * @return 是否产出成功
     */
    public static boolean removeFile(String bucket, List<DeleteObject> objectList) {
        try {
            MinioConfig.minioClient.removeObjects(RemoveObjectsArgs.builder()
                    .bucket(bucket)
                    .objects(objectList)
                    .bypassGovernanceMode(true)
                    .build());
            return true;
        } catch (Exception e) {
            throw new JiliException(e.getMessage());
        }
    }

}
