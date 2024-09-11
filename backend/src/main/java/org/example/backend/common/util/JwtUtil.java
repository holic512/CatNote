/**
 * CreateTime: 2024-08-27
 * Description: 用作与JWT工具类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.common.util;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.example.backend.common.enums.UserRole;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtil {

    // 使用 Key 对象来生成密钥
    private static final Dotenv dotenv = Dotenv.configure().directory("backend/.env").load();
    private static final String SECRET_KEY = dotenv.get("JWT_SECRET");

    // 过期时间(单位:秒)
    public static final int ACCESS_EXPIRE = 3600;

    // 私有构造函数，防止外部实例化
    private JwtUtil() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // 获取密钥对象
    private static SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }

    // 生成 JWT
    public static String generateToken(String uid, UserRole role) {
        return Jwts.builder()
                .signWith(getSigningKey()) // 签名
                .issuedAt(new Date()) // 签发时间
                .expiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRE * 1000)) // 过期时间
                .subject(uid) // 用户ID作为主题
                .claim("role", role) // 用户角色作为自定义声明
                .compact();
    }

    // 解析 JWT
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    // 解析 JWT 并获取 UID、角色和有效性
    public static Map<String, Object> getClaimsFromToken(String token) {
        Map<String, Object> result = new HashMap<>();
        try {
            Claims claims = parseToken(token);
            result.put("uid", claims.getSubject());
            result.put("role", claims.get("role"));
            result.put("valid", true);
        } catch (Exception e) {
            result.put("valid", false);
        }
        return result;
    }

}
