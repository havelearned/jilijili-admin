package top.jilijili.module.entity.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

/**
 * @author Amani
 * @date 2023年07月15日 6:29
 */
@Data
@SuperBuilder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MusicSongVo {

    private List<MusicSingerVo> singerList;
    private MusicAlbumVo album;
    /**
     *
     */

    private String songId;

    /**
     * 专辑id
     */
    private String albumId;

    /**
     * 歌手id
     */
    private String singerIds;

    /**
     * 歌曲名称
     */

    private String name;

    /**
     * 状态::2 草稿, 1 上架, 0 下架
     */

    private Integer status;

    /**
     * 歌词
     */

    private String lyric;

    /**
     * 歌曲源
     */

    private String sourceFile;

    /**
     *
     */

    private Date createdTime;

    /**
     *
     */
    private Date updatedTime;
}
