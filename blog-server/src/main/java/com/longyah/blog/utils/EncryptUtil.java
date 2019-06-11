package com.longyah.blog.utils;

import org.springframework.util.DigestUtils;

import java.util.Random;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
public class EncryptUtil {
    private static final String RANDOM_CHAR_SOURCE = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

    /**
     * 生成随机盐
     *
     * @param len 长度
     * @return
     */
    private static String getRandomSalt(int len) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        while (builder.length() < len) {
            builder.append(RANDOM_CHAR_SOURCE.charAt(random.nextInt(RANDOM_CHAR_SOURCE.length())));
        }
        return builder.toString().toLowerCase();
    }


    /**
     * 加密明文密码
     *
     * @param plain   明文密码
     * @param saltLen 盐长度
     * @return
     */
    public static String encryptPassword(String plain, int saltLen) {
        String salt = getRandomSalt(saltLen);
        return DigestUtils.md5DigestAsHex((plain + salt).getBytes()) + salt;
    }

    /**
     * 校验密码
     *
     * @param password  明文密码
     * @param encrypted 密文
     * @return
     */
    public static boolean validatePassword(String password, String encrypted) {
        String salt = encrypted.substring(32);
        return encrypted.substring(0, 32).equals(DigestUtils.md5DigestAsHex((password + salt).getBytes()));
    }
}
