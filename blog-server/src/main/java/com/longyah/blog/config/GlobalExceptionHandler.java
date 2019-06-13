package com.longyah.blog.config;

import com.longyah.blog.vo.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.CredentialsExpiredException;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.longyah.blog.consts.ResponseCodeConsts.*;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResultVo<Void> handBindExceptions(BindException bindException) {
        String message = bindException.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ", "", "."));
        return new ResultVo<>(PARAMETER_ERROR.getCode(), message);
    }

    @ExceptionHandler(AuthenticationException.class)
    public ResultVo<Void> handAuthenticationException(AuthenticationException authenticationException) {
        if (authenticationException instanceof UsernameNotFoundException) {
            return new ResultVo<>(USERNAME_NOT_FOUND.getCode(), "用户名不存在");
        }
        if (authenticationException instanceof CredentialsExpiredException) {
            return new ResultVo<>(TOKEN_EXPIRED.getCode(), authenticationException.getMessage());
        }
        if (authenticationException instanceof BadCredentialsException) {
            return new ResultVo<>(ILLEGAL_TOKEN.getCode(), authenticationException.getMessage());
        }
        if (authenticationException instanceof InsufficientAuthenticationException) {
            return new ResultVo<>(NO_ACCESS.getCode(), "没有权限访问");
        }
        log.warn("", authenticationException);
        return new ResultVo<>(40000, authenticationException.getMessage());
    }
}
