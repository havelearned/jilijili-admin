package wang.jilijili.music.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wang.jilijili.common.core.pojo.dto.QueryDto;

import java.util.Date;

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
