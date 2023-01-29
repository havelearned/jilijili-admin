package wang.jilijili.music.pojo.dto;

import java.util.Date;

/**
 * @Auther: Amani
 * @Date: 2023/1/24 11:26
 * @Description:
 */
public class UserDto {
    private String id;
    private String nickname;
    private String username;
    private Date createdTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }
}
