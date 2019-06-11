package com.longyah.blog.vo;

import lombok.Getter;
import lombok.ToString;

/**
 * @author RenQiang
 * @date 2019/6/5
 */
@Getter
@ToString
public class ResultVo<T> {
    private Integer code;
    private String message;
    private T data;

    public ResultVo() {
        this.code = 20000;
    }

    public ResultVo(T data) {
        this.code = 20000;
        this.data = data;
    }

    public ResultVo(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
