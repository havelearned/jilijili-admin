package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.Version;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.Date;

/**
 * 文件管理表
 *
 * @TableName file_management
 */
@TableName(value = "file_management")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public final class FileManagement implements Serializable {
    /**
     *
     */
    @TableId(value = "file_id")
    private Long fileId;

    /**
     * 文件名称
     */
    @TableField(value = "file_name")
    private String fileName;

    /**
     * 文件路径
     */
    @TableField(value = "file_path")
    private String filePath;

    /**
     * 文件类型id
     */
    @TableField(value = "file_type")
    private String fileType;

    /**
     *
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    /**
     *
     */
    @TableField(value = "created_time")
    private Date createdTime;


    /**
     * 文件版本号
     */
    @Version
    @TableField(value = "version")
    private Integer version;

    @TableField(value = "size")
    private Long size;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}