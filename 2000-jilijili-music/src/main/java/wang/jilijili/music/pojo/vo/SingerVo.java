package wang.jilijili.music.pojo.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Amani
 * @date 2023年03月20日 23:38
 */
@Data
public class SingerVo {

    private String singerId;
    /**
     * 歌手id
     */

    protected String id;

    /**
     * 歌手名称
     */
    protected String singerName;

    /**
     * 是否禁用
     */
    protected Integer locked;

    /**
     * 歌手简介
     */

    protected String singerDetails;

    /**
     * 歌手头像
     */

    protected String singerPhoto;




    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    protected Date createdTime;

}
