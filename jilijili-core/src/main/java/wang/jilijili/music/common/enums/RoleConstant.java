package wang.jilijili.music.common.enums;

/**
 * 角色字符
 * SpringSecurity 角色授权 会默认添加一个前缀ROLE_
 *
 * @author Amani
 * @date 2023年02月17日 16:46
 */
public interface RoleConstant {

    /**
     * 普通用户
     * */
     String ROLE_USER = "USER";

    /**
     * 超级管理员
     * */
     String ROLE_SUPER_ADMIN = "SUPER_ADMIN";

}
