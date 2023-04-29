package wang.jilijili.common.core.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import wang.jilijili.common.utils.ServletUtils;

/**
 * 基础控制器
 *
 * @author Amani
 * @date 2023年04月21日 23:36
 */
public class BaseController {
    /**
     * 获取request
     */
    public HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    public HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    public HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 页面跳转
     */
    public String redirect(String url) {
        return String.format("redirect:%s", url);
    }
}
