/**
 * 文件名称: accountInfo.java
 * 描述: 用于表示用户账户信息的数据传输对象 (DTO)。
 * 作者: holic512
 * 创建日期: 2024-10-12
 * 版本: 1.0
 * 用法: 该类用于存储和传输用户的账户相关信息，包括头像、昵称、联系方式等。
 */

package org.example.backend.user.settings.dto;

import lombok.Data;

@Data
public class AccountInfo {
    private Long id;
    private String avatar;
    private String nickname;
    private String uid;
    private String username;
    private String email;
    private String gender;
    private Integer age;
    private String contact_info;
    private String bio;

    public AccountInfo(Long id, String username, String email) {
        this.id = id;
        this.username = username;
        this.email = email;
    }

    public AccountInfo(String nickname, String gender, Integer age, String contact_info, String bio) {
        this.nickname = nickname;
        this.gender = gender;
        this.age = age;
        this.contact_info = contact_info;
        this.bio = bio;
    }

    public void setUserProfile(AccountInfo accountInfo) {
        this.nickname = accountInfo.getNickname();
        this.gender = accountInfo.getGender();
        this.age = accountInfo.getAge();
        this.contact_info = accountInfo.getContact_info();
        this.bio = accountInfo.getBio();
    }

}
