package top.jilijili.system.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.jilijili.system.common.enums.LoginOptionEnum;
import top.jilijili.common.entity.Result;

import java.util.Map;

/**
 * 登录上下文
 */
@Service
@RequiredArgsConstructor
public class LoginStrategyContext {


    private Map<String, LoginStrategy> loginStrategyMap;

    @Autowired
    public void setLoginStrategyMap(Map<String, LoginStrategy> loginStrategyMap) {
        this.loginStrategyMap = loginStrategyMap;
    }


    public Result<String> executeLogin(String username, String password, LoginOptionEnum loginOptionEnum) {
        LoginStrategy loginStrategy = this.loginStrategyMap.get(loginOptionEnum.getOption());
        return  loginStrategy.login(username, password);
    }

}
