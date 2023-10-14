package top.jilijili.system.service.impl;

import org.springframework.stereotype.Service;
import top.jilijili.common.entity.Result;

/**
 * @author Amani
 * @date 2023年06月22日 9:45
 */
@Service(value = "wxLoginService")
public class WxLoginServiceImpl extends AbstractLoginStrategyImpl {
    @Override
    public Result<String> login(String username, String password) {
        return Result.ok("微信登陆");
    }

    @Override
    public void initialization() {

    }

    @Override
    public Result<String> executeLogin(String username, String password) {
        return null;
    }
}
