package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * 歌词表
 *
 * @TableName music
 */
@TableName(value = "music")
@Data
public class Music implements Serializable {
    /**
     *
     */
    @TableId(value = "id")
    private String id;

    /**
     * 歌曲名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 歌曲状态::DRAFT 草稿, PUBLISHED 上架, CLOSED 下架
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 歌曲简介
     */
    @TableField(value = "description")
    private String description;

    /**
     * 歌曲路径
     */
    @TableField(value = "music_filepath")
    private String musicFilepath;

    /**
     * 歌手id
     */
    @TableField(value = "singer_id")
    private String singerId;

    /**
     * 专辑id
     */
    @TableField(value = "album_id")
    private String albumId;

    /**
     *
     */
    @TableField(value = "created_time")
    private Date createdTime;

    /**
     *
     */
    @TableField(value = "updated_time")
    private Date updatedTime;

    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}