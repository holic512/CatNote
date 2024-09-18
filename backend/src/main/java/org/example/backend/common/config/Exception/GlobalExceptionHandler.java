/**
 * File Name: GlobalExceptionHandler.java
 * Description: 全局异常拦截
 * Author: holic512
 * Created Date: 2024-09-17
 * Version: 1.0
 * Usage:
 * 针对与全局异常进行拦截 获取信息并反馈内容
 */
package org.example.backend.common.config.Exception;

import cn.dev33.satoken.exception.SaTokenException;
import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SaTokenException.class)
    public SaResult handlerSaTokenException(SaTokenException e) {

        // 根据不同异常细分状态码返回不同的提示
        if(e.getCode() == 11012) {
            return SaResult.error("未读取有效token");
        }


        // 默认的提示
        return SaResult.error("服务器繁忙，请稍后重试...,错误信息:" + e.getMessage());
    }
}
