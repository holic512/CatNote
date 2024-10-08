/**
 * File Name: UserGenderEnum.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-09-20
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.common.enums.user;

import lombok.Getter;

@Getter
public enum UserGenderEnum {
    MALE("male"),
    FEMALE("female"),
    OTHER("other");

    private final String value;

    UserGenderEnum(String value) {
        this.value = value;
    }

    public static UserGenderEnum fromValue(String value) {
        for (UserGenderEnum gender : UserGenderEnum.values()) {
            if (gender.value.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Unknown gender: " + value);
    }


}
