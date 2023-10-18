package top.jilijili.module.pojo.entity.sys;

import io.minio.messages.Owner;
import io.minio.messages.ResponseDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Map;

/**
 * @author Amani
 * @date 2023年09月29日 19:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class FileItem {
    /**
     * 表示对象的ETag（Entity Tag），用于标识对象的版本。
     */
    private String etag;

    /**
     * ：表示对象的名称（文件名或对象键）。
     */
    private String objectName;

    /**
     * 表示对象的最后修改时间戳。
     */
    private ResponseDate lastModified;

    /**
     * 所有者
     */
    private Owner owner;

    /**
     * 文件的大小
     * 这里是按照byte计算
     * 9.4Kb * 1024 = 9625.6byte
     * 9625.6 字节 ÷ 1,048,576 = 0.0091753 MB（约为0.0092 MB）
     */
    private long size;

    /**
     * storageClass：表示对象的存储类别，通常包括标准、低频或归档等选项。
     */
    private String storageClass;

    /**
     * 最新内容
     */
    private boolean isLatest;

    /**
     * 版本id
     */
    private String versionId;

    /**
     * 用户元数据
     */
    private Map<String, String> userMetadata;
    /**
     * 是否目录
     */
    private String isDir;
    /**
     * 编码类型
     */
    private String encodingType;
}