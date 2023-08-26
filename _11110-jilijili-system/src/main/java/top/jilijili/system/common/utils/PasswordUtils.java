package top.jilijili.system.common.utils;

import cn.hutool.crypto.SecureUtil;

public class PasswordUtils {

    // 加密密码
    public static String encryptPassword(String password) {
        return SecureUtil.md5(password);
        // 或者使用其他加密算法，例如：SecureUtil.sha256(password)
    }

    // 验证密码是否匹配
    public static boolean verifyPassword(String password, String encryptedPassword) {
        return encryptPassword(password).equals(encryptedPassword);
    }
}
