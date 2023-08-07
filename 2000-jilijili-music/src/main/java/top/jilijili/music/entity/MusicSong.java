package top.jilijili.music.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌曲表
 * @TableName music_song
 */
@TableName(value ="music_song")
@Data
public class MusicSong implements Serializable {
    /**
     * 
     */
    @TableId(value = "song_id")
    private Long songId;

    /**
     * 专辑id
     */
    @TableField(value = "album_id")
    private Long albumId;

    /**
     * 歌曲名称
     */
    @TableField(value = "name")
    private String name;

    /**
     * 状态::2 草稿, 1 上架, 0 下架
     */
    @TableField(value = "status")
    private Integer status;

    /**
     * 歌词
     */
    @TableField(value = "lyric")
    private String lyric;

    /**
     * 歌曲源
     */
    @TableField(value = "source_file")
    private String sourceFile;

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

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}