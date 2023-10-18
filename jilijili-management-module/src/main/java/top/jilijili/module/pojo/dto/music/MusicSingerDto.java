package top.jilijili.module.pojo.dto.music;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.module.pojo.dto.SuperDto;

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
public class MusicSingerDto extends SuperDto {
    /**
     * 歌手id
     */
    private Long singerId;

    /**
     * 歌手名称
     */
    private String name;

    /**
     * 歌手头像
     */
    private String photo;


    /**
     * 歌手简介
     */
    private String desc;


    /**
     * 歌手状态（1正常 0停用）
     */
    private String status;

    /**
     * 歌手类型:"0" 歌手 "1"乐队 "2"其他',
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