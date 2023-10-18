package top.jilijili.module.pojo.entity.music;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.jeecgframework.poi.excel.annotation.Excel;

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
    @Excel(name = "歌曲id", width = 20)
    private Long songId;

    /**
     * 专辑id
     */
    @TableField(value = "album_id")
    private Long albumId;

    /**
     * 歌手id
     */
    @TableField(value = "singer_ids")
    private String singerIds;



    /**
     * 歌曲名称
     */
    @TableField(value = "`name`")
    @Excel(name = "歌曲名称", width = 20)
    private String name;

    /**
     * 状态::2 草稿, 1 上架, 0 下架
     */
    @TableField(value = "`status`")
    @Excel(name = "歌曲状态", width = 20, replace = {"草稿_2", "上架_1","下架_0"})
    private Integer status;

    /**
     * 歌词
     */
    @TableField(value = "lyric")
    @Excel(name = "歌词", width = 20)
    private String lyric;

    /**
     * 歌曲源
     */
    @TableField(value = "source_file")
    @Excel(name = "播放文件", width = 20)
    private String sourceFile;

    /**
     * 
     */
    @TableField(value = "created_time",fill = FieldFill.INSERT)
    private Date createdTime;

    /**
     * 
     */
    @TableField(value = "updated_time",fill = FieldFill.UPDATE)
    private Date updatedTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}