package wang.jilijili.music.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wang.jilijili.common.core.pojo.dto.QueryDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 专辑表
 *
 * @TableName alibum
 */

@Data
public class AlibumDto extends QueryDto implements Serializable {


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