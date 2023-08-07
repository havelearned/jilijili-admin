package top.jilijili.reptile.music;

import cn.hutool.http.HttpUtil;

import java.util.Date;
import java.util.HashMap;

/**
 * @author Amani
 * @date 2023年07月26日 15:05
 */
public class SingerRetile {

    public static String singerUrl = "https://api.liumingye.cn/m/api/artist/list";
    public static HashMap<String, Object> param = new HashMap<>(20);


    public static void main(String[] args) {
        param.put("initial", 0);
        param.put("page", 1);
        param.put("token", "20230327.66be823540626c925a4114229aad7542");
        param.put("_t", new Date().getTime());


        String response = HttpUtil.post(singerUrl, param,5000);
        System.out.println(response);
    }
}
