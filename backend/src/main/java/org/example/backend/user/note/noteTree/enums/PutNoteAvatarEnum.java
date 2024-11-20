/**
 * 文件名: PutAvatarEnum.java
 * 描述: 表示更新笔记头像的状态枚举。
 * 作者: holic512
 * 创建日期: 2024-11-20
 * 版本: 1.0
 * 用途:
 * 用于指示更新笔记头像操作的结果，例如操作是否成功或失败的原因。
 */
package org.example.backend.user.note.noteTree.enums;

public enum PutNoteAvatarEnum {
    NOTE_NOT_FOUND,
    NOTE_NOT_OWNED_BY_USER,
    UPDATE_SUCCESS;
}
