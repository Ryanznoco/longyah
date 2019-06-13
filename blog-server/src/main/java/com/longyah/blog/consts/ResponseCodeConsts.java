package com.longyah.blog.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author RenQiang
 * @date 2019-06-11
 */
@Getter
@AllArgsConstructor
public enum ResponseCodeConsts {
    /**
     * 用户名或密码错误
     */
    LOGIN_FAIL(60204, "Account and password are incorrect"),
    /**
     * 参数错误
     */
    PARAMETER_ERROR(60201, "Parameter error"),
    /**
     * 验证码错误
     */
    CAPTCHA_ERROR(40019, "Captcha error"),
    /**
     * 用户名不存在
     */
    USERNAME_NOT_FOUND(40101, "Username not found"),
    /**
     * 没有权限/权限不够
     */
    NO_ACCESS(20020, "No access"),
    /**
     * Token 无效
     */
    ILLEGAL_TOKEN(50008, "Illegal token"),
    /**
     * 已在其它客户端登录
     */
    OTHER_CLIENTS_LOGGED_IN(50012, "Other clients logged in"),
    /**
     * Token 已过期
     */
    TOKEN_EXPIRED(50014, "Token expired");

    int code;
    String defaultMsg;
}
