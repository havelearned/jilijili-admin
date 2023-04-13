package wang.jilijili.music.pojo.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import wang.jilijili.common.core.pojo.dto.QueryDto;
import wang.jilijili.common.group.Insert;
import wang.jilijili.common.group.Updata;

import java.util.List;

/**
 * service和dao层传输对象
 *
 * @author Amani
 * @date 2023年03月09日 10:31
 */
@Data
public class MusicDto extends QueryDto {

    @NotBlank(message = "歌手id不能为空", groups = {Updata.class})
    private String id;

    /**
     * 歌手id
     */
    @NotBlank(message = "歌手id不能为空", groups = {Insert.class})
    private List<String> singerId;

    /**
     * 专辑id 可以为空
     */
    private String albumId;

    @NotBlank(message = "歌手名称不能为空", groups = {Insert.class})
    private String name;

    private Integer status;

    private String description;


}
