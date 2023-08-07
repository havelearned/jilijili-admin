package top.jilijili.common.core.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.jilijili.common.core.pojo.vo.Result;

/**
 * 系统配置管理
 *
 * @author Amani
 * @date 2023年05月05日 12:02
 */
@RestController
@RequestMapping("/sys/config")
@Tag(name = "系统配置管理")
public class SysConfigController {

    /**
     * 获取系统配置信息
     *
     * @return
     */
    @GetMapping("/configData")
    public Result<Object> getConfigData() {

        return null;
    }

}
