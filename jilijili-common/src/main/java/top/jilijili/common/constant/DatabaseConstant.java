package top.jilijili.common.constant;

/**
 * 数据库字段常量
 *
 * @author Amani
 * @date 2023年03月01日 8:39
 */
public final class DatabaseConstant {
    private DatabaseConstant() {
    }

    public static final String ROLE_USER_ID = "2LrCBBMHDhbLHmdyzRZ8CBdfQc6";
    public static final String ROLE_SUPER_ADMIN_ID = "2LrC8wCWcA8AGUV65OeLCJiQRAw";

    /**
     * 用户搜索字段
     */
    public static final String ID = "id";
    public static final String GENDER = "gender";
    public static final String USERNAME = "username";
    public static final String NICKNAME = "nickname";
    public static final String CREATED_TIME = "created_time";
    public static final String ENABLED = "enabled";

    /**
     * 歌手搜索字段
     */
    public static final String SINGERNAME = "singer_name";

    /**
     * 专辑搜索字段
     */
    public static final String ALBUMNAME = "album_name";


}
