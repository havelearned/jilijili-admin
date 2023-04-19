package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 歌词表
 *
 * @author admin
 * @TableName music
 */
@TableName(value = "music")
@Data
public class Music extends SuperEntity implements Serializable {


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
     * 专辑id
     */
    @TableField(value = "album_id")
    private String albumId;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}