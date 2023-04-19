package wang.jilijili.music.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Amani
 * @date 2023年04月17日 19:01
 */
@Data
public class MusicDetailVo {
    private AlibumVo alibumVo;
    private List<SingerVo> singerVos;

    private String musicId;

    private String id;

    private String name;

    private Integer status;

    private String description;

    private String musicFilepath;

    private String albumId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;

}
