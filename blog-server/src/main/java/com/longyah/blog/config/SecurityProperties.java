package com.longyah.blog.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author RenQiang
 * @date 2019-06-13
 */
@Getter
@Setter
@ConfigurationProperties(prefix = "app.security")
public class SecurityProperties {
    public static int PASSWORD_EXPIRE_DAYS;
    public static final String JWT_BEARER = "Bearer ";

    private String jwtTokenHeader;
    private String jwtSecret;
    private Integer jwtTokenExpireSeconds;

    public void setPasswordExpireDays(int passwordExpireDays) {
        PASSWORD_EXPIRE_DAYS = passwordExpireDays;
    }
}
