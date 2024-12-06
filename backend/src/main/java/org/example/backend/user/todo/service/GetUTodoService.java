/**
 * File Name: GUTService.java
 * Description: 该接口提供了用户待办事项（Todos）相关服务的方法，包括获取所有待办事项、按类别获取待办事项等。
 * Author: holic512
 * Created Date: 2024-12-02
 * Version: 1.0
 * Usage: 实现此接口以提供待办事项管理服务。通过实现这些方法，开发者可以使系统支持对用户待办事项的查询功能。
 */

package org.example.backend.user.todo.service;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.user.todo.enums.GetUTContextEnum;

/**
 * 定义了用于获取用户待办事项的服务接口。
 */
public interface GetUTodoService {

    /**
     * 获取指定用户的全部待办事项。
     *
     * @param userId 用户唯一标识符。
     * @return 包含状态枚举和结果对象的Pair，其中第一个元素是操作上下文的状态枚举 {@link GetUTContextEnum}，
     *         第二个元素是与请求相关的数据，可能是待办事项列表或其他类型的响应数据。
     */
    Pair<GetUTContextEnum, Object> getAllTodos(Long userId);

    /**
     * 获取指定用户的全部待办事项分类。
     *
     * @param userId 用户唯一标识符。
     * @return 包含状态枚举和结果对象的Pair，其中第一个元素是操作上下文的状态枚举 {@link GetUTContextEnum}，
     *         第二个元素是与请求相关的数据，可能是待办事项分类列表或其他类型的响应数据。
     */
    Pair<GetUTContextEnum, Object> getAllTodoCategory(Long userId);

    /**
     * 根据类别ID获取指定用户的待办事项。
     *
     * @param userId     用户唯一标识符。
     * @param categoryId 类别唯一标识符。
     * @return 包含状态枚举和结果对象的Pair，其中第一个元素是操作上下文的状态枚举 {@link GetUTContextEnum}，
     *         第二个元素是与请求相关的数据，可能是特定类别的待办事项列表或其他类型的响应数据。
     */
    Pair<GetUTContextEnum, Object> getAllTodosByCategoryId(Long userId, Long categoryId);
}