package top.jilijili.system.ws;

import cn.dev33.satoken.stp.StpUtil;

import javax.security.auth.Subject;
import java.security.Principal;

/**
 * @author Amani
 * @date 2023年08月07日 12:01
 */
public class WsPrincipal implements Principal {
    @Override
    public String getName() {
        return StpUtil.getLoginIdAsString();
    }

    @Override
    public boolean implies(Subject subject) {
        return Principal.super.implies(subject);
    }
}
