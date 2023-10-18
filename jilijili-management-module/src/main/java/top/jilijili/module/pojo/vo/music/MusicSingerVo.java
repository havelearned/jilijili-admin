package top.jilijili.module.pojo.vo.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;

/**
 * 歌手表
 *
 * @TableName music_singer
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class MusicSingerVo {
    /**
     * 歌手id
     */
    private String singerId;

    /**
     * 歌手名称
     */
    private String name;

    /**
     * 歌手简介
     */
    private String desc;

    /**
     * 歌手头像
     */
    private String photo;

    /**
     * 歌手状态（0正常 1停用）
     */
    private String status;

    /**
     *歌手类型:"0" 歌手 "1"乐队 "2"其他',
     */
    private String type;

    /**
     *
     */
    private Date createdTime;

    /**
     *
     */
    private Date updatedTime;

}