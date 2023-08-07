package top.jilijili.system.entity.vo;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.List;

/**
 * @author Amani
 * @date 2023年06月22日 11:12
 */
@Data
@Builder
@Accessors(chain = true)
public class SysUserVo {
    private List<SysRoleVo> roleList;
    /**
     * 用户ID
     */
    private String userId;

    /**
     * 用户名
     */
    private String username;

    /**
     * 用户昵称
     */
    private String nickname;


    /**
     * 用户类型（00 游客、01 普通登录(系统)、02 qq、03 微信登录、04 gitee登录、05 github ）
     */
    private Integer userType;

    /**
     * 头像地址
     */
    private String avatar;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号码
     */
    private String phonenumber;

    /**
     * 是否锁定,1-是,0-否
     */
    private Integer locked;

    /**
     * 是否可用,1-是,0-否
     */
    private Integer enabled;

    /**
     * 最后登录IP
     */
    private String lastLoginIp;

    /**
     * 最后登录IP
     */
    private Date lastLoginTime;

    /**
     * 创建时间
     */
    private Date createdTime;

    /**
     * 更新时间
     */
    private Date updatedTime;

}
