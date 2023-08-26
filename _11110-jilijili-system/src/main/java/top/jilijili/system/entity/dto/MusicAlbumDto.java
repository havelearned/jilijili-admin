package top.jilijili.system.entity.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;
import top.jilijili.system.service.group.Insert;
import top.jilijili.system.service.group.Update;

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
public class MusicAlbumDto extends SuperDto {
    /**
     * 专辑id
     */
    private Long albumId;


    /**
     * 一个或者多个歌手id 添加修改必传
     */
    @NotNull(message = "请先选择歌手", groups = {Insert.class, Update.class})
    private List<Long> singerIds;

    /**
     * 专辑名称
     */
    @NotNull(message = "专辑名称不能为空", groups = {Insert.class, Update.class})
    @NotBlank(message = "专辑名称不能为空", groups = {Insert.class, Update.class})
    private String albumName;

    /**
     * 专辑介绍
     */

    private String desc;

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