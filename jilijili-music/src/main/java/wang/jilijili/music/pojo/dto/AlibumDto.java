package wang.jilijili.music.pojo.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wang.jilijili.common.core.pojo.dto.QueryDto;

import java.io.Serializable;
import java.util.Date;

/**
 * 专辑表
 *
 * @author admin
 * @TableName alibum
 */

@Data
public class AlibumDto extends QueryDto implements Serializable {
    private String id;

    /**
     * 专辑名称
     */
    @NotBlank(message = "专辑名称不能为空")
    private String albumName;

    /**
     * 专辑介绍
     */
    private String details;

    /**
     * 专辑封面
     */
    @NotBlank(message = "专辑封面不能为空")
    private String albumImg;


    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
}