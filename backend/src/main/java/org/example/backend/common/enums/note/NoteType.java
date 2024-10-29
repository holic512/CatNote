/**
 * File Name: NoteType.java
 * Description: 该枚举类定义了笔记的类型，目前仅支持普通笔记类型。
 * Author: holic512
 * Created Date: 2024-10-28
 * Version: 1.0
 * Usage:
 * - 通过 `NoteType.Normal` 访问普通笔记类型的枚举值。
 * - 通过 `NoteType.Normal.getValue()` 获取普通笔记类型的整数值。
 */
package org.example.backend.common.enums.note;

import lombok.Getter;

@Getter
public enum NoteType {

    Normal(1);



    private final Integer value;
    NoteType(Integer value) {
        this.value = value;
    }

}
