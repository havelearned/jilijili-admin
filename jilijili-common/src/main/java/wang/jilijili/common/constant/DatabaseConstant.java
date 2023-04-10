package wang.jilijili.common.constant;

/**
 * 数据库字段常量
 *
 * @author Amani
 * @date 2023年03月01日 8:39
 */
public interface DatabaseConstant {
     String ROLE_USER_ID = "2LrCBBMHDhbLHmdyzRZ8CBdfQc6";
     String ROLE_SUPER_ADMIN_ID = "2LrC8wCWcA8AGUV65OeLCJiQRAw";

    /**
     * 用户搜索字段
     * */
     String ID = "id";
     String GENDER = "gender";
     String USERNAME = "username";
     String NICKNAME = "nickname";
     String CREATED_TIME = "created_time";
     String ENABLED = "enabled";

    /**
     * 歌手搜索字段
     */
    String SINGERTYPE = "singer_type";
    String SINGERNAME = "singer_name";

    /**
     * 专辑搜索字段
     * */
    String ALBUMNAME="album_name";


}
