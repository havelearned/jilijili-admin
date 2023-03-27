package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serializable;

/**
 * 专辑表
 *
 * @author admin
 * @TableName alibum
 */
@TableName(value = "alibum")
@Data
public class Alibum extends SuperEntity implements Serializable {


    /**
     * 专辑名称
     */
    @TableField(value = "album_name")
    private String albumName;

    /**
     * 专辑介绍
     */
    @TableField(value = "details")
    private String details;

    /**
     * 专辑封面
     */
    @TableField(value = "album_img")
    private String albumImg;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}