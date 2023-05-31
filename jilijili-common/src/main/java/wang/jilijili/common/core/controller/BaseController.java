package wang.jilijili.common.core.controller;

import com.baomidou.mybatisplus.extension.service.IService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import wang.jilijili.common.utils.ServletUtils;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;

/**
 * 基础控制器
 *
 * @author Amani
 * @date 2023年04月21日 23:36
 */
@Slf4j
public class BaseController<T, S extends IService<T>> {
    S service;

    public void setService(S service) {
        this.service = service;
    }

    protected String getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (!(authentication instanceof AnonymousAuthenticationToken)) {
            return authentication.getName();
        } else {
            return null;
        }

    }

    /**
     * 获取request
     */
    protected HttpServletRequest getRequest() {
        return ServletUtils.getRequest();
    }

    /**
     * 获取response
     */
    protected HttpServletResponse getResponse() {
        return ServletUtils.getResponse();
    }

    /**
     * 获取session
     */
    protected HttpSession getSession() {
        return getRequest().getSession();
    }

    /**
     * 页面跳转
     */
    protected String redirect(String url) {
        return String.format("redirect:%s", url);
    }


    /**
     * 响应xls文件
     *
     * @param response
     * @param fileName
     */
    protected void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            fileName= URLEncoder.encode(fileName, StandardCharsets.UTF_8);
            response.setContentType("application/octet-stream;charset=ISO8859-1");
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            response.setHeader("content-disposition", "attachment;filename=" + fileName + ".xls");
            response.setHeader("Pragma", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
            response.setHeader("Expires", "0");
            response.setHeader("Last-Modified", new Date().toString());
            response.setHeader("ETag", String.valueOf(System.currentTimeMillis()));

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }


}
