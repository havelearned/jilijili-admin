package wang.jilijili.music.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 专辑表
 *
 * @author admin
 * @TableName alibum
 */
@Data
public class AlibumVo implements Serializable {

    private String albumId;

    private String id;
    /**
     * 专辑名称
     */

    private String albumName;

    /**
     * 专辑介绍
     */

    private String details;

    /**
     * 专辑封面
     */

    private String albumImg;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}