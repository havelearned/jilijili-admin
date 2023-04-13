package wang.jilijili.music.pojo.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import java.util.List;

/**
 * @author Amani
 * @date 2023年04月09日 16:50
 */
@Data
public class AlibumCreateRequest {

    @NotBlank(message = "专辑主键不能为空")
    private String id;

    /**
     * 歌手id
     */
    @NotNull(message = "歌手不能为空")
    @Size(min = 1, message = "歌手不能为空")
    private List<String> singerId;

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
