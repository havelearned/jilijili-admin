package wang.jilijili.music.pojo.entity;

import com.baomidou.mybatisplus.annotation.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 *
 * @TableName user
 */

@TableName(value = "user")
@Data
public class User extends SuperEntity implements Serializable, UserDetails {

    @TableId(type = IdType.INPUT)
    @TableField(fill = FieldFill.INSERT)
    protected String id;

    @TableField(exist = false)
    List<Role> roles;


    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 性别
     */
    private String gender;

    /**
     * 是否锁定,1-是,0-否
     */
    @TableLogic
    private Integer locked = 0;

    /**
     * 是否可用,1-是,0-否
     */
    @TableField(value = "enabled")
    private Integer unseal = 1;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录IP
     */
    @TableField(fill = FieldFill.INSERT)
    private Date lastLoginTime;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}