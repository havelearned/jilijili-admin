package wang.jilijili.music.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import wang.jilijili.music.pojo.entity.User;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Amani
 * @date 2023年02月12日 22:13
 */
@Data
public class UserQueryDto implements Serializable {

    private String id;

    // TODO 构建查询字段
    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;



    /**
     * 性别
     */
    private String gender;

    /**
     * 是否可用,1-是,0-否
     */
    private Integer unseal = 1;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date createdTime;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date specifyTime;


}
