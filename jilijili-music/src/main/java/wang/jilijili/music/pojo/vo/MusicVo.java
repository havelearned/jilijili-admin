package wang.jilijili.music.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wang.jilijili.music.enums.MusicStatus;

import java.util.Date;

/**
 * 返回前端的对象
 *
 * @author Amani
 * @date 2023年03月09日 10:27
 */
@Data
public class MusicVo {

    private String id;

    private String name;

    private MusicStatus status;

    private String description;

    private String musicFilepath;

    private String singerId;

    private String albumId;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date updatedTime;


}
