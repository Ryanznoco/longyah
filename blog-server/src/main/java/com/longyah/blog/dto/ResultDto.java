package com.longyah.blog.dto;

import lombok.Getter;
import lombok.ToString;

/**
 * @author RenQiang
 * @date 2019-06-11
 */
@Getter
@ToString
public class ResultDto<T> {
    private boolean success;
    private String msg;
    private T data;

    public ResultDto() {
        this.success = true;
    }

    public ResultDto(T data) {
        this.success = true;
        this.data = data;
    }

    public ResultDto(String msg) {
        this.success = false;
        this.msg = msg;
    }
}
