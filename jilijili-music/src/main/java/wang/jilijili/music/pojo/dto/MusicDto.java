package wang.jilijili.music.pojo.dto;

import lombok.Data;
import wang.jilijili.common.core.pojo.dto.QueryDto;

/**
 * service和dao层传输对象
 *
 * @author Amani
 * @date 2023年03月09日 10:31
 */
@Data
public class MusicDto extends QueryDto {

    private String id;

    /**
     * 歌手id
     */

    private String singerId;

    /**
     * 专辑id 可以为空
     */
    private String albumId;

    private String name;

    private Integer status;

    private String description;


}
