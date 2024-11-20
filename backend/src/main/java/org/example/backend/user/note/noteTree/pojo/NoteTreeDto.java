/**
 * File Name: NoteTreeDto.java
 * Description: 该类用于表示笔记树中的节点信息，包括节点的 ID、名称和是否为叶子节点（即是否为文件）。
 * 主要用于前端展示文件夹和笔记的层级结构。
 * Author: holic512
 * Created Date: 2024-10-21
 * Version: 1.1
 * Usage:
 * - 通过 `NoteTreeDto` 对象可以构建和传递笔记树的节点信息。
 * - 通常在后端服务中将文件夹和笔记信息转换为 `NoteTreeDto` 对象，然后传递给前端进行展示。
 * - 前端可以根据 `NoteTreeDto` 对象中的 `leaf` 属性判断节点是文件夹还是文件，并进行相应的渲染。
 */
package org.example.backend.user.note.noteTree.pojo;

import lombok.Data;
import org.example.backend.user.note.noteTree.enums.TreeType;

import java.util.List;

@Data
public class NoteTreeDto {

    // ID
    private Long id;

    // 代表这 节点的名称
    private String label;

    // type 此 note代表的 状态 0 文件夹 1 笔记
    private TreeType type;

    // 用于存储文件夹的  子节点
    private List<NoteTreeDto> children;

    // 自定义头像
    private char[] Avatar;


    // 用于存储文件夹的 构造函数
    NoteTreeDto(Long id, String label, TreeType type, char[] Avatar) {
        this.id = id;
        this.label = label;
        this.type = type;
        this.Avatar = Avatar;

    }


}
