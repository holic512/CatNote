/**
 * File Name: SecurityJwtFilter.java
 * Description: security jwt 过滤器
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 对发起的请求进行过滤,验证token的有效性和身份信息
 */
package org.example.backend.common.config.Security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.backend.common.util.JwtUtil;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

@Configuration
public class SecurityJwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, @NonNull HttpServletResponse response, @NonNull FilterChain filterChain) throws ServletException, IOException {
        final String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String token = authorizationHeader.substring(7);
            Map<String, Object> userMap;

            try {
                userMap = JwtUtil.getClaimsFromToken(token);
            } catch (Exception e) {
                // 处理 JWT 解析异常
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\": \"Invalid JWT Token\"}");
                response.flushBuffer();
                return; // 停止后续的过滤器链
            }

            boolean valid = (boolean) userMap.get("valid");
            if (!valid) {
                // 如果 JWT 无效
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                response.getWriter().write("{\"error\": \"Invalid JWT Token\"}");
                response.flushBuffer();
                return; // 停止后续的过滤器链
            }

            String uid = (String) userMap.get("uid");
            String rule = (String) userMap.get("role");

            if (uid != null && rule != null) {
                List<SimpleGrantedAuthority> authorities = Collections.singletonList(new SimpleGrantedAuthority("ROLE_" + rule));
                UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(uid, null, authorities);
                authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authentication);

            }
        }

        // 继续处理过滤链
        filterChain.doFilter(request, response);
    }
}
