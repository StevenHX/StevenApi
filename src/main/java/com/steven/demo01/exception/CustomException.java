package com.steven.demo01.exception;

import com.steven.demo01.domain.CommonResult;
import lombok.Getter;

/**
 * @author Joetao
 * Created at 2018/8/24.
 */
@Getter
public class CustomException extends RuntimeException{
    private CommonResult result;

    public CustomException(CommonResult resultJson) {
        this.result = resultJson;
    }
}