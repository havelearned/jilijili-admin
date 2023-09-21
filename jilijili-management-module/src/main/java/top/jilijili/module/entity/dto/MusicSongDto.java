package top.jilijili.module.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Date;
import java.util.List;

/**
 * 歌曲表
 *
 * @TableName music_song
 */
@Data
@SuperBuilder
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class MusicSongDto extends SuperDto {
    /**
     * 歌手列表
     */
    private List<ChooseEntityDto> singerList;

    /**
     * 专辑信息
     */
    private List<ChooseEntityDto> album;

    /**
     * 歌曲id
     */
    private Long songId;

    /**
     * 专辑id
     */
    private Long albumId;

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


