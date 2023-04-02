package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serial;
import java.io.Serializable;

/**
 * 歌手表
 * @TableName singer
 */
@TableName(value ="singer")
@Data
public class Singer extends SuperEntity implements Serializable {


    /**
     * 歌手名称
     */
    @TableField(value = "singer_name")
    private String singerName;

    /**
     * 歌手简介
     */
    @TableField(value = "singer_details")
    private String singerDetails;

    /**
     * 歌手头像
     */
    @TableField(value = "singer_photo")
    private String singerPhoto;

    /**
     * 歌手类型
     */
    @TableField(value = "singer_type")
    private Integer singerType;

    /**
     * 是否禁用
     */
    @TableField(value = "locked")
    @TableLogic
    private Integer locked;



    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}