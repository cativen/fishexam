package com.fishexam.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

/**
 * md5密码加密工具类
 */
@Component
public class PasswordLock {
    /**
     * 加密格式
     */
    public static final String algorithmName = "md5";

    /**
     * 密码加密
     *
     * @param password 明文密码
     * @return 经过加密后的密码
     */
    public String passwordLocks(String password) {
        // md5加密方法,salt为盐值,hashIterations为加密次数
        return String.valueOf(new SimpleHash(algorithmName, password, "fish", 3));
    }
}
