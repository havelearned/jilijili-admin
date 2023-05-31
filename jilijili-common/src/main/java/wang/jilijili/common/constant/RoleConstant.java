package wang.jilijili.common.constant;

/**
 * 角色字符
 * SpringSecurity 角色授权 会默认添加一个前缀ROLE_
 *
 * @author Amani
 * @date 2023年02月17日 16:46
 */
public final class RoleConstant {

    private RoleConstant() {
    }

    /**
     * 普通用户
     */
    public static final String ROLE_USER = "USER";

    /**
     * 超级管理员
     */
    public static final String ROLE_SUPER_ADMIN = "SUPER_ADMIN";

}
