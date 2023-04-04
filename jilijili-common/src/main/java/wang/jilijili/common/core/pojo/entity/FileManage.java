package wang.jilijili.common.core.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * @TableName file_manage
 */
@TableName(value = "file_manage")
@Data
public class FileManage extends SuperEntity implements Serializable {


    /**
     * 文件名称(不包含后缀)
     */
    @TableField(value = "filename")
    private String filename;

    /**
     * 文件全路径url
     */
    @TableField(value = "filepath")
    private String filepath;

    /**
     * 文件类型: .png  .jpg
     */
    @TableField(value = "type")
    private String type;

    /**
     * 文件大小 字节
     */
    @TableField(value = "filesize")
    private Long filesize;

    /**
     * 是否锁定
     */
    @TableField(value = "locked")
    @TableLogic
    private Integer locked;


    /**
     * 访问时间
     */
    @TableField(value = "access_time")
    private Date accessTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}