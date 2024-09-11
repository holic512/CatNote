/**
 * File Name: SecurityConfig.java
 * Description: security 配置类
 * Author: holic512
 * Created Date: 2024-09-11
 * Version: 1.0
 * Usage:
 * 在此对Security进行配置,如接口对应的权限等
 */
package org.example.backend.common.config.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final SecurityJwtFilter securityJwtFilter;

    @Autowired
    public SecurityConfig(SecurityJwtFilter securityJwtFilter) {
        this.securityJwtFilter = securityJwtFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                // 禁用csrf
                .csrf(AbstractHttpConfigurer::disable)

                // 禁用表单登录
                .formLogin(AbstractHttpConfigurer::disable)

                // 配置权限访问
                .authorizeHttpRequests(auth -> auth

                        // 认证相关的接口允许访问
                        .requestMatchers("/admin/auth/**").permitAll()
                        .requestMatchers("/user/auth/**").permitAll()

                        // 接口权限配置
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())


                // 禁用会话
                .sessionManagement((session) -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 添加 JWT 过滤器到链中
                .addFilterBefore(securityJwtFilter, UsernamePasswordAuthenticationFilter.class)

                // 状态反馈
                .exceptionHandling((exceptions) -> exceptions
                        // 未登录或认证失败 401
                        .authenticationEntryPoint(new CustomAuthenticationEntryPoint())
                        // 权限不足 403
                        .accessDeniedHandler(new CustomAccessDeniedHandler())
                )

                .build();
    }
}
