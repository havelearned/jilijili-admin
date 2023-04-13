package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 歌手和歌曲的中间表
 *
 * @author admin
 * @TableName singer_music
 */
@TableName(value = "singer_music")
@Data
@NoArgsConstructor
public class SingerMusic implements Serializable {
    /**
     * id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private String id;

    /**
     * 歌手id
     */
    @TableField(value = "singer_id")
    private String singerId;

    /**
     * 歌曲id
     */
    @TableField(value = "music_id")
    private String musicId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public SingerMusic(String singerId, String musicId) {
        this.singerId = singerId;
        this.musicId = musicId;
    }
}