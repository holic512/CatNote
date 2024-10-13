/**
 * File Name: OUserInfoDto.java
 * Description: 用于存储层 与 数据库进行查询交互 获取在线玩家详细信息
 * Author: holic512
 * Created Date: 2024-10-09
 * Version: 1.0
 * Usage:
 * 用于存储层 与 数据库进行查询交互
 */
package org.example.backend.admin.userMm.dto;

import lombok.Data;

@Data
public class OUserInfoDto {
    private Long id;
    private String uid;
    private String username;
    private String email;
    private String CreateTime;

    public OUserInfoDto(Long id, String uid, String username, String email) {
        this.id = id;
        this.uid = uid;
        this.username = username;
        this.email = email;
    }
}
