package top.jilijili.mall.currency.feign;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import top.jilijili.common.entity.Result;
import top.jilijili.common.group.Insert;
import top.jilijili.common.heandler.JiliException;
import top.jilijili.module.pojo.dto.sys.SysUserDto;
import top.jilijili.module.pojo.entity.shop.Orders;
import top.jilijili.module.pojo.vo.sys.SysUserVo;

/**
 * openFeign接口
 * URL：就是远程端需要调用接口的服务URL路径，name：就是服务名，value和name一样
 *
 * @author Amani
 * @date 2023年10月17日 0:48
 */
@FeignClient(value = "11110-jilijili-system", url = "${figen.client.url.sys}",
        fallback = JiliException.class)
public interface UserServiceFigen {

    @GetMapping("/console/sendOrderInfo")
    void sendOrderInfo(@RequestParam Orders orders);

    @GetMapping("/sysUser/me")
    Result<SysUserVo> getUserinfo(@RequestParam(value = "token", defaultValue = "") String token);

    @PostMapping("/sysUser/login")
    Result<String> userLogin(@RequestBody @Validated(value = {Insert.class}) SysUserDto sysUserDto);

    @GetMapping("/sysUser/list")
    Result<Page<SysUserVo>> getList(@RequestParam SysUserDto sysUserDto);
}
