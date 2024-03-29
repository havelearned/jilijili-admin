package top.jilijili.common.utils;

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

    /*-----------------redisKey-----------------*/


    /**
     * Redis 秒杀分布式锁
     * 格式:
     */
    public final static String SECKILL_LOCK = "seckill_lock:";

    /**
     * Redis 商品库存预热
     * 格式: key:商品id
     */
    public final static String PRODUCT_HEA_KEY = "product_heat_key:";

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


    /*-----------------导入导出模板-----------------*/
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

    /**
     * 通知交换机
     */
    public final static String NOTIFY_EXCHANGE = "notify_exchange";
}
