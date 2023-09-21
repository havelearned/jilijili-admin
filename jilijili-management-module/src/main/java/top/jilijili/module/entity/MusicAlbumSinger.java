package top.jilijili.module.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 专辑和歌手信息表
 *
 * @TableName music_album_singer
 */
@TableName(value = "music_album_singer")
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
@Data
public class MusicAlbumSinger implements Serializable {
    /**
     * 专辑id
     */
    @TableField(value = "album_id")
    private Long albumId;

    /**
     * 歌手id
     */
    @TableField(value = "singer_id")
    private Long singerId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}