package top.jilijili.system.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

/**
 * 专辑表
 *
 * @TableName music_album
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@Accessors(chain = true)
public class MusicAlbumVo {
    /**
     *
     */

    private String albumId;


    /**
     * 专辑名称
     */

    private String albumName;

    /**
     * 专辑介绍
     */
    private String desc;

    /**
     * 歌手
     */
    private List<MusicSingerVo> singerList;

    /**
     * 专辑封面
     */

    private String albumCover;

    /**
     * 发布时间
     */

    private Date createdTime;

    /**
     *
     */

    private Date updatedTime;


}