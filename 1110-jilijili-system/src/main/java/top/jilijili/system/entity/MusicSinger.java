package top.jilijili.system.entity;


import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelVerify;

import java.io.Serializable;
import java.util.Date;

/**
 * 歌手表
 *
 * @TableName music_singer
 */
@TableName(value = "music_singer")
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class MusicSinger implements Serializable {
    /**
     * 歌手id
     */
    @TableId(value = "singer_id")
    @Excel(name = "歌手id", width = 20)
    private Long singerId;

    /**
     * 歌手名称
     */
    @TableField(value = "`name`")
    @Excel(name = "歌手名称", width = 20)
    @ExcelVerify(notNull = true, regexTip = "歌手名称不能为空")
    private String name;


    /**
     * 歌手头像
     */
    @TableField(value = "photo")
    @Excel(name = "歌手头像", width = 40, type = 2, imageType = 4, savePath = "E:\\idea_project\\jilijili-admin\\upload\\image")
    private String photo;

    /**
     * 歌手简介
     */
    @TableField(value = "`desc`")
    @Excel(name = "歌手简介", width = 20)
    private String desc;

    /**
     * 歌手状态（0正常 1停用）
     */
    @TableField(value = "`status`")
    @Excel(name = "歌手状态", width = 20, replace = {"正常_1", "停用_0"})
    private String status;

    /**
     * 歌手类型:"0" 歌手 "1"乐队 "2"其他',
     */
    @TableField(value = "`type`")
    private String type;

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