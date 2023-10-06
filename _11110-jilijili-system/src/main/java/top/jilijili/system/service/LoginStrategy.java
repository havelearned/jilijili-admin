package top.jilijili.system.service;


import top.jilijili.module.entity.vo.Result;

/**
 * 登录策略
 */
public interface LoginStrategy {
    Result<String> login(String username, String password);
}
