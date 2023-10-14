package top.jilijili.system.common.utils;

import java.util.List;

/**
 * 作用于:
 * Redis key value
 * 通用常量名称
 * 系统配置名称
 * 等等
 *
 * @author Amani
 * @date 2023年06月24日 18:09
 */
public class KeyConstants {
    private KeyConstants() {
    }

    /**
     * Redis key验证码
     * 格式: CAPTCHA_KEY:时间戳
     */
    public final static String CAPTCHA_KEY = "CAPTCHA_KEY:";

    /**
     * Redis key ip访问限制
     * 格式: LOGIN_RESTRICTION:IP地址
     */
    public final static String LOGIN_RESTRICTION = "LOGIN_RESTRICTION:";


    /**
     * Minio 文件路径
     * 歌手数据导入模板
     */
    public final static String SINGER_DATA_IMPORT_TEMPLATE = "/Excel/歌手数据导入模板.xlsx";
    public final static String SONG_DATA_IMPORT_TEMPLATE = "/Excel/歌曲数据导入模板.xlsx";
    public final static String Album_DATA_IMPORT_TEMPLATE = "/Excel/专辑数据导入模板.xlsx";

    public final static List<String> SONG_DATA_IMPORT_TEMPLATE_LIST = List.of(SINGER_DATA_IMPORT_TEMPLATE,
            SINGER_DATA_IMPORT_TEMPLATE,
            Album_DATA_IMPORT_TEMPLATE);


    /*---------------------------------消息队列----------------------------------------------*/
    /**
     * 系统通知队列
     */
    public final static String SYS_NOTIFY_QUEUE = "sys_notify_queue";
    public final static String SYS_NOTIFY_ALL_QUEUE = "sys_notify_all_queue";
    public final static String SYS_NOTIFY_ALL_SCHEDULED_QUEUE = "sys_notify_all_scheduled_queue";
    /**
     * 系统通知路由
     */
    public final static String SYS_ROUTER = "SYS_ROUTER";
    public final static String SYS_ROUTER_NOTIFY_ALL = "sys_router_notify_all";
    public final static String SYS_ROUTER_NOTIFY_SCHEDULED = "sys_router_notify_scheduled";

    /**
     * 通知交换机
     */
    public final static String NOTIFY_EXCHANGE = "notify_exchange";
}
