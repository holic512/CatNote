/**
 * File Name: PatchUTodoService.java
 * Description: 该接口提供了用户待办事项（Todos）更新服务的方法，包括完成待办事项和重新打开已完成的待办事项。
 * Author: holic512
 * Created Date: 2024-12-06
 * Version: 1.0
 * Usage: 实现此接口以提供待办事项状态变更的服务。通过实现这些方法，开发者可以使系统支持对用户待办事项状态的更新操作。
 */

package org.example.backend.user.todo.service;

import org.example.backend.user.todo.enums.PatchUTContextEnum;

/**
 * 定义了用于更新用户待办事项状态的服务接口。
 */
public interface PatchUTodoService {

    /**
     * 将指定用户的特定待办事项标记为已完成。
     *
     * @param UserId 用户唯一标识符。
     * @param todoId 待办事项唯一标识符。
     * @return 操作结果的状态枚举 {@link PatchUTContextEnum}，表示操作的成功与否及上下文信息。
     */
    PatchUTContextEnum completeTodo(Long UserId, Long todoId);

    /**
     * 将指定用户的特定已完成待办事项重新打开，使其变为未完成状态。
     *
     * @param UserId 用户唯一标识符。
     * @param todoId 待办事项唯一标识符。
     * @return 操作结果的状态枚举 {@link PatchUTContextEnum}，表示操作的成功与否及上下文信息。
     */
    PatchUTContextEnum reopenTodo(Long UserId, Long todoId);
}