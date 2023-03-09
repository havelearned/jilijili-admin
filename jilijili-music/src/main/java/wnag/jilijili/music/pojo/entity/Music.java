package wnag.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.music.common.enums.MusicStatus;
import wang.jilijili.music.pojo.entity.SuperEntity;

import java.io.Serial;
import java.io.Serializable;

/**
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
     * 歌曲状态:1可用 0不可用
     */
    @TableField(value = "music_status")
    private MusicStatus musicStatus;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}