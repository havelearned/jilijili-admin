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
    protected String singerName;

    /**
     * 歌手简介
     */
    @TableField(value = "singer_details")
    protected String singerDetails;

    /**
     * 歌手头像
     */
    @TableField(value = "singer_photo")
    protected String singerPhoto;


    /**
     * 是否禁用
     */
    @TableField(value = "locked")
    @TableLogic
    protected Integer locked;



    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}