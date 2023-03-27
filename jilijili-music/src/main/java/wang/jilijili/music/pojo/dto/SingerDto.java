package wang.jilijili.music.pojo.dto;

import lombok.Data;
import wang.jilijili.common.core.pojo.dto.QueryDto;

/**
 * @author Amani
 * @date 2023年03月20日 23:38
 */
@Data
public class SingerDto extends QueryDto {


    /**
     * 歌手id
     */
    private String id;

    /**
     * 歌手名称
     */
    private String singerName;

    /**
     * 是否禁用
     */
    private Integer locked;

    /**
     * 歌手简介
     */

    private String singerDetails;

    /**
     * 歌手头像
     */

    private String singerPhoto;

    /**
     * 歌手类型
     */
    private Integer singerType;


}
