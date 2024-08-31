/**
 * CreateTime: 2024-08-27
 * Description: 用作与JWT工具类
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.example.backend.enums.UserRole;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


public class JwtUtil {

    // 使用 Key 对象来生成密钥
    private static final SecretKey SECRET_KEY = Jwts.SIG.HS256.key().build();


    // 过期时间(单位:秒)
    public static final int ACCESS_EXPIRE = 3600;

    // 私有构造函数，防止外部实例化
    private void JwtUtils() {
        throw new UnsupportedOperationException("Utility class cannot be instantiated");
    }

    // 生成 JWT
    public static String generateToken(String uid, UserRole role) {
        return Jwts.builder()
                .subject(uid) // 用户ID作为主题
                .claim("role", role) // 用户角色作为自定义声明
                .issuedAt(new Date()) // 签发时间
                .expiration(new Date(System.currentTimeMillis() + ACCESS_EXPIRE)) // 过期时间
                .signWith(SECRET_KEY) // 签名
                .compact();
    }

    // 解析 JWT
    public static Claims parseToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    // 从 JWT 中获取 UID
    public static String getUidFromToken(String token) {
        return parseToken(token).getSubject();
    }

    // 从 JWT 中获取角色
    public static String getRoleFromToken(String token) {
        return (String) parseToken(token).get("role");
    }

    // 验证 JWT 是否有效
    public static boolean validateToken(String token) {
        try {
            parseToken(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // 解析 JWT 并获取 UID、角色和有效性
    public static Map<String, Object> getClaimsFromToken(String token) {
        Map<String, Object> result = new HashMap<>();
        try {
            Claims claims = Jwts.parser()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            result.put("uid", claims.getSubject());
            result.put("role", claims.get("role"));
            result.put("valid", true);
        } catch (Exception e) {
            result.put("valid", false);
        }
        return result;
    }

    // 使用演示
    // public static void main(String[] args) {
    //
    //     // 传入 uid 和 role 生成 token
    //     String uid = "478522016";
    //     String role = "admin";
    //     String token = generateToken(uid, role);
    //
    //     // 解析 token 的 uid role 和有效性
    //     Map<String, Object> info = JwtUtil.getClaimsFromToken(token);
    //     System.out.println(info.get("uid"));
    //     System.out.println(info.get("role"));
    //     System.out.println(info.get("valid"));
    // }

}
