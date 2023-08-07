package top.jilijili.common.constant;

/**
 * redis keys
 *
 * @author admin
 */
public final class RedisKeyConstant {
    private RedisKeyConstant() {
    }

    public static final String  LOGIN_USER_INFO= "login_user_info::";

    /**
     * 1 hour = 3600 seconds
     */
    public static final Long HOUR_24 = 3600L * 24L;

    /**
     * 表单重复提交 key+ip+hashcode
     */
    public static final String KEY_NO_REPEAT_SUBMIT = "SUBMIT-KEY-";

    /**
     * 菜单key 过期时间24小时
     */
    public static final String KEY_MENU = "KEY_MENU::";
}
