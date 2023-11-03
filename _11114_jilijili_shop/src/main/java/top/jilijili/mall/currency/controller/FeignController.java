package top.jilijili.mall.currency.controller;

import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import top.jilijili.common.entity.Result;
import top.jilijili.common.group.Insert;
import top.jilijili.mall.currency.feign.UserServiceFigen;
import top.jilijili.module.pojo.dto.sys.SysUserDto;
import top.jilijili.module.pojo.vo.sys.SysUserVo;

/**
 * @author Amani
 * @date 2023年10月28日 18:14
 */
@RestController
@AllArgsConstructor
public class FeignController {

    private final UserServiceFigen userServiceFigen;


    @GetMapping("/sysUser/me")
    public Result<SysUserVo> getUserinfo(@RequestParam(value = "token", defaultValue = "") String token){
        return userServiceFigen.getUserinfo(token);
    }

    /**
     * 远程调用系统登录接口
     * @param sysUserDto
     * @return
     */
    @PostMapping("/sysUser/login")
    Result<String> userLogin(@RequestBody @Validated(value = {Insert.class}) SysUserDto sysUserDto){
        return userServiceFigen.userLogin(sysUserDto);
    }
}
