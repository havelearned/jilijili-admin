package top.jilijili.system.common.utils;

import jakarta.servlet.http.HttpServletResponse;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author Amani
 * @date 2023年07月13日 16:39
 */
public class WebUtil {
    private WebUtil() {
    }

    /**
     * 设置文件下载请求头
     *
     * @param filename 文件名称
     * @return 请求头
     */
    public static void setDownloadRequestHeader(HttpServletResponse response, String filename) {
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(filename, StandardCharsets.UTF_8));
        response.setContentType("application/x-download");
        response.setCharacterEncoding("UTF-8");
        response.addHeader("Pargam", "no-cache");
        response.addHeader("Cache-Control", "no-cache");
        // 设置响应头
        try {
            response.flushBuffer();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
