/**
 * File Name: UserProfile.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-09-20
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.backend.common.enums.user.UserGenderEnum;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "user_profiles")
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "uid", nullable = false, unique = true)
    private String uid;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private UserGenderEnum gender;

    @Column(name = "age")
    private Integer age;

    @Column(name = "bio", columnDefinition = "TEXT")
    private String bio;

    @Column(name = "contact_info")
    private String contactInfo;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "updated_at", nullable = false)
    private LocalDateTime updatedAt;

    // 可以在实体类中添加 @PrePersist 和 @PreUpdate 方法来自动处理创建时间和更新时间
    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = LocalDateTime.now();
    }

    public UserProfile() {
    }

    public static class Builder {
        private Long id;
        private String uid;
        private String nickname;
        private UserGenderEnum gender;
        private Integer age;
        private String bio;
        private String contactInfo;

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder uid(String uid) {
            this.uid = uid;
            return this;
        }

        public Builder nickname(String nickname) {
            this.nickname = nickname;
            return this;
        }

        public Builder gender(UserGenderEnum gender) {
            this.gender = gender;
            return this;
        }

        public Builder age(Integer age) {
            this.age = age;
            return this;
        }

        public Builder bio(String bio) {
            this.bio = bio;
            return this;
        }

        public Builder contactInfo(String contactInfo) {
            this.contactInfo = contactInfo;
            return this;
        }

        public UserProfile build() {
            UserProfile profile = new UserProfile();
            profile.setId(id);
            profile.setUid(uid);
            profile.setNickname(nickname);
            profile.setGender(gender);
            profile.setAge(age);
            profile.setBio(bio);
            profile.setContactInfo(contactInfo);
            return profile;
        }
    }
}
