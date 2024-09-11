/**
 * File Name: CustomAccessDeniedHandler.java
 * Description: security 消息响应体
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 当用户带有错误的权限,使用接口,会返回"code: 403, message: Forbidden 的响应体
 */
package org.example.backend.common.config.Security;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

public class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request,
                       HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException {
        response.setContentType("application/json;charset=UTF-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getWriter().write("{\"code\": 403, \"message\": \"Forbidden\"}");
    }
}
