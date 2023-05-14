package wang.jilijili.common.constant;

/**
 * redis keys
 *
 * @author admin
 */
public interface RedisKeyConstant {

    /**
     * 1 hour = 3600 seconds
     * */
    Long HOUR_24 = 3600L * 24L;

    /**
     * 表单重复提交 key+ip+hashcode
     */
    String KEY_NO_REPEAT_SUBMIT = "SUBMIT-KEY-";

    /**
     * 菜单key 过期时间24小时
     */
    String KEY_MENU="KEY_MENU::";
}
