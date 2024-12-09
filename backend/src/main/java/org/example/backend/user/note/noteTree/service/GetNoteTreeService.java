/**
 * File Name: GNoteTreeService.java
 * Description: 该接口定义了与笔记树相关的服务方法，主要用于查询用户的文件夹和笔记信息，并构建笔记树结构。
 * Author: holic512
 * Created Date: 2024-10-16
 * Version: 1.0
 * Usage:
 * - 通过 `GNoteTreeService` 接口可以查询用户的文件夹和笔记信息。
 * - 通常在控制器层中调用这些方法，获取文件夹和笔记的层级结构，并返回给前端进行展示。
 * - 前端可以根据返回的 `NoteTreeDto` 列表构建和展示笔记树结构。
 */
package org.example.backend.user.note.noteTree.service;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.user.note.noteTree.enums.GetUNTContextEnum;
import org.example.backend.user.note.noteTree.pojo.NoteTreeDto;

import java.util.HashMap;
import java.util.List;

public interface GetNoteTreeService {

    /**
     * 根据笔记文件夹查询父文件夹ID。
     *
     * @param NoteId 笔记的唯一标识符。
     * @return 返回父文件夹的ID。如果笔记没有父文件夹，则返回null或特定值（如-1），具体取决于业务逻辑。
     */
    Long getFolderIdByNoteId(long NoteId);

    /**
     * 根据用户ID查询该用户的完整笔记树。
     *
     * @param NoteId  笔记的唯一标识符。
     * @param FolderId 父文件夹的ID，用于过滤属于特定文件夹的笔记树。
     * @return 包含用户笔记树结构的列表，其中每个元素都是一个 {@link NoteTreeDto} 对象。
     */
    List<NoteTreeDto> getNoteTreeListByNoteId(long NoteId, long FolderId);

    /**
     * 获取笔记的描述信息。
     *
     * @param UserId 用户的唯一标识符。
     * @param NoteId 笔记的唯一标识符。
     * @return 返回一个Pair对象，其中第一个元素是 {@link GetUNTContextEnum} 枚举类型，表示获取上下文的状态；
     *         第二个元素是包含笔记描述信息的字符串。
     */
    Pair<GetUNTContextEnum, String> getNoteDescription(Long UserId, Long NoteId);

    /**
     * 获取文件夹的描述信息。
     *
     * @param UserId 用户的唯一标识符。
     * @param NoteId 文件夹中某笔记的唯一标识符（可能是为了关联到特定文件夹）。
     * @return 返回一个Pair对象，其中第一个元素是 {@link GetUNTContextEnum} 枚举类型，表示获取上下文的状态；
     *         第二个元素是包含文件夹描述信息的字符串。
     */
    Pair<GetUNTContextEnum, String> getFolderDescription(Long UserId, Long NoteId);

    /**
     * 获取笔记的创建时间、更新时间。
     *
     * @param UserId 用户的唯一标识符。
     * @param NoteId 笔记的唯一标识符。
     * @return 返回一个Pair对象，其中第一个元素是 {@link GetUNTContextEnum} 枚举类型，表示获取上下文的状态；
     *         第二个元素是一个字符串，包含了笔记的创建时间、更新时间，格式由业务逻辑决定。
     */
    Pair<GetUNTContextEnum, HashMap<String,String>>  getNoteCreatedAtAndUpdatedAt(Long UserId, Long NoteId);

    /**
     * 获取笔记的最后保存时间。
     *
     * @param UserId 用户的唯一标识符。
     * @param NoteId 笔记的唯一标识符。
     * @return 返回一个Pair对象，其中第一个元素是 {@link GetUNTContextEnum} 枚举类型，表示获取上下文的状态；
     *         第二个元素是包含笔记最后保存时间的字符串，格式由业务逻辑决定。
     */
    Pair<GetUNTContextEnum, String> getNoteSaveAt(Long UserId, Long NoteId);

    /**
     * 获取文件夹的创建时间、更新时间。
     *
     * @param UserId 用户的唯一标识符。
     * @param folderId 文件夹的唯一标识符。
     * @return 返回一个Pair对象，其中第一个元素是 {@link GetUNTContextEnum} 枚举类型，表示获取上下文的状态；
     *         第二个元素是一个字符串，包含了笔记的创建时间、更新时间，格式由业务逻辑决定。
     */
    Pair<GetUNTContextEnum, HashMap<String,String>>  getFolderCreatedAtAndUpdatedAt(Long UserId, Long folderId);
}