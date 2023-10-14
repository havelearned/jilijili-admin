package top.jilijili.system.service.impl;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import top.jilijili.common.entity.Result;
import top.jilijili.system.service.LoginStrategy;

/**
 * @author Amani
 * @date 2023年06月22日 9:54
 */
@Getter
@Setter
@Slf4j
public abstract class AbstractLoginStrategyImpl implements LoginStrategy {

    public Result<String> login(String username, String password) {
        // TODO 解密操作
        initialization();
        return executeLogin(username, password);
    }

    public abstract void initialization();

    public abstract Result<String> executeLogin(String username, String password);
}
