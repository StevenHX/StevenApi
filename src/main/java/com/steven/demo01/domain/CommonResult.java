package com.steven.demo01.domain;

import lombok.Data;

@Data
public class CommonResult<T> {
    private Integer code;
    private String msg;
    private T data;

    public CommonResult(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public CommonResult(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static <T> CommonResult<T> success(T t) {
        return new CommonResult<T>(200, "操作成功", t);
    }

    public static <T> CommonResult<T> error(T t) {
        return new CommonResult<T>(500, "操作失败", t);
    }
    public static <T> CommonResult<T> error(String msg, T t) {
        return new CommonResult<T>(500, msg, t);
    }
    public static <T> CommonResult<T> unVerify(T t) {
        return new CommonResult<T>(401, "认证失败", t);
    }
}