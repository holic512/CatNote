/**
 * CreateTime: 2024-08-27
 * Description: 用户登录的数据传输对象
 * Version: 1.0
 * Author: holic512
 */
package org.example.backend.user.auth.dto;

import lombok.Getter;
import lombok.Setter;
import org.example.backend.common.enums.user.UserStatusEnum;

@Setter
@Getter
public class AuthDto {
    private String uid;
    private String password;
    Integer status;
    String code;


    public AuthDto(String uid, Integer status, String code) {
        this.uid = uid;
        this.status = status;
        this.code = code;
    }

    public AuthDto(String uid, String password) {
        this.uid = uid;
        this.password = password;
    }

    @Override
    public String toString() {
        return "AuthDto [uid=" + uid + ", password=" + password + "]";
    }
}
