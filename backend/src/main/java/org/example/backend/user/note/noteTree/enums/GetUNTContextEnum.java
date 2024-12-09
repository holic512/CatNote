/**
 * File Name: GetContextEnum.java
 * Description: Get User Note Tree 的 请求状态码
 * Author: holic512
 * Created Date: 2024-12-09
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.note.noteTree.enums;

public enum GetUNTContextEnum {
    SUCCESS,
    // 用户与笔记Id不匹配
    USER_NOTE_ID_MISMATCH,
    /**
     * 表示笔记还未编辑过
     */
    UNEDITED,
}
