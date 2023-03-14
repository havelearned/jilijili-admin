package wang.jilijili.music.pojo.entity;


import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.common.enums.MusicStatus;
import wang.jilijili.common.pojo.entity.SuperEntity;

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
    @TableField(value = "status")
    private MusicStatus status;

    /**
     * 歌曲信息
     * */
    @TableField(value = "description")
    private String description;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}