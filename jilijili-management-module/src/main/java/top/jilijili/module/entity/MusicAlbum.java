package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.jeecgframework.poi.excel.annotation.Excel;

import java.io.Serializable;
import java.util.Date;

/**
 * 专辑表
 *
 * @TableName music_album
 */
@TableName(value = "music_album")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class MusicAlbum implements Serializable {
    /**
     *
     */
    @TableId(value = "album_id")
    @Excel(name = "专辑id", width = 20)
    private Long albumId;


    /**
     * 专辑名称
     */
    @TableField(value = "album_name")
    @Excel(name = "专辑名称", width = 20)
    private String albumName;

    /**
     * 专辑介绍
     */
    @TableField(value = "`desc`")
    @Excel(name = "简介", width = 20)
    private String desc;

    /**
     * 专辑封面
     */
    @TableField(value = "album_cover")
    @Excel(name = "封面", width = 20)
    private String albumCover;

    /**
     * 发布时间
     */
    @TableField(value = "created_time", fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     *
     */
    @TableField(value = "updated_time", fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}