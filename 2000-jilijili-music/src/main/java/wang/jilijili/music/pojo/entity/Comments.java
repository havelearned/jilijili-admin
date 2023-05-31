package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * @author admin
 * @TableName comments
 */
@TableName(value = "comments")
@Data
public class Comments extends SuperEntity implements Serializable {


    /**
     * 评论内容
     */
    @TableField(value = "details")
    private String details;

    /**
     * 评论类型 1歌单2专辑3歌曲
     */
    @TableField(value = "type")
    private Integer type;

    /**
     * 目标id，可能是歌单id、专辑id、歌曲id
     */
    @TableField(value = "targetid")
    private String targetid;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}