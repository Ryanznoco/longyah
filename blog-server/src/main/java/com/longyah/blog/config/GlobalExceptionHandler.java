package com.longyah.blog.config;

import com.longyah.blog.vo.ResultVo;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

import static com.longyah.blog.consts.ResponseCodeConsts.PARAMETER_ERROR;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BindException.class)
    public ResultVo<Void> handBindExceptions(BindException bindException) {
        String message = bindException.getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining(", ", "", "."));
        return new ResultVo<>(PARAMETER_ERROR.getCode(), message);
    }
}
