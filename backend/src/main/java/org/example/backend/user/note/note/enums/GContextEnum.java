/**
 * File Name: GContextEnum.java
 * Description: 用于 get 请求 获取 笔记 context 的状态枚举
 * Author: holic512
 * Created Date: 2024-11-12
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.note.enums;

public enum GContextEnum {
    Success,            // 成功
    NoteOwnerNotMatch,  // 表示笔记的所有人ID与给定的ID不匹配
}
