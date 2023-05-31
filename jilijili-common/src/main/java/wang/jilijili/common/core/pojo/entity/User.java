package wang.jilijili.common.core.pojo.entity;

import cn.afterturn.easypoi.excel.annotation.Excel;
import cn.afterturn.easypoi.excel.annotation.ExcelCollection;
import cn.afterturn.easypoi.excel.annotation.ExcelTarget;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import wang.jilijili.common.enums.Gender;
import wang.jilijili.common.pojo.entity.SuperEntity;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

/**
 * 用户表
 *
 * @author admin
 * @TableName user
 */
@ExcelTarget("user")
@TableName(value = "user")
@Schema(title = "用户", name = "用户实体类")
@Data
public class User extends SuperEntity implements Serializable, UserDetails {


    @TableField(exist = false)
    @ExcelCollection(name = "用户角色", orderNum = "4")
    List<Role> roles;


    /**
     * 用户名
     */
    @Excel(name = "用户名称", orderNum = "1", needMerge = true)
    private String username;

    /**
     * 用户昵称
     */
    @Excel(name = "用户昵称", orderNum = "2", needMerge = true)
    private String nickname;

    /**
     * 加密后的密码
     */
    private String password;

    /**
     * 性别
     */
    @Excel(name = "性别", orderNum = "3", needMerge = true, replace = {"UNKNOWN_未知", "ALL_男女皆可", "MALE_男", "FEMALE_女"})
    private Gender gender;

    /**
     * 是否锁定,1-是,0-否
     */
    @TableLogic
    @Excel(name = "锁定状态", orderNum = "4", needMerge = true, replace = {"锁定_1", "正常_0"})
    private Integer locked = 0;

    /**
     * 是否可用,1-是,0-否
     */
    @Excel(name = "状态", orderNum = "5", needMerge = true, replace = {"正常_1", "停用_0"})
    @TableField(value = "enabled")
    private Integer unseal = 1;

    /**
     * 最后登录IP
     */
    @Excel(name = "IP", orderNum = "6", needMerge = true)
    private String lastLoginIp;

    /**
     * 最后登录IP
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(timezone = "GMT+8", pattern = "yyyy-MM-dd HH:mm:ss")
    @TableField(value = "last_login_time", fill = FieldFill.INSERT_UPDATE)
    private Date lastLoginTime;


    @Serial
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        return new ArrayList<GrantedAuthority>(
                roles.stream()
                        .map(item -> new SimpleGrantedAuthority(item.getName()))
                        .toList());
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