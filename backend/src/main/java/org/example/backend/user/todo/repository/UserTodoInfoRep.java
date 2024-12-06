/**
 * File Name: GUTTodoInfoRep.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-03
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.todo.repository;

import org.example.backend.common.entity.TodoInfo;
import org.example.backend.user.todo.dto.TodoCombinedDTO;
import org.example.backend.user.todo.enums.TodoStatusEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface UserTodoInfoRep extends JpaRepository<TodoInfo, Long> {

    @Query("SELECT new org.example.backend.user.todo.dto.TodoCombinedDTO(" +
            "ti.id, ti.title, ti.description, ti.startDate, ti.dueDate, ti.status, ti.isDeleted, " +
            "tc.id, tc.name, tc.type) " +
            "FROM TodoInfo ti " +
            "LEFT JOIN TodoCategory tc ON ti.category_id = tc.id " +  // 改为 LEFT JOIN
            "WHERE ti.user_id = :userId AND (tc.isDeleted = false OR tc.isDeleted IS NULL)")
        // 处理没有分类的情况
    List<TodoCombinedDTO> findCombinedTodoInfoByUserId(@Param("userId") Long userId);

    @Query("SELECT new org.example.backend.user.todo.dto.TodoCombinedDTO(" +
            "ti.id, ti.title, ti.description, ti.startDate, ti.dueDate, ti.status, ti.isDeleted, " +
            "tc.id, tc.name, tc.type) " +
            "FROM TodoInfo ti " +
            "JOIN TodoCategory tc ON ti.category_id = tc.id " +
            "WHERE ti.user_id = :userId AND (tc.isDeleted = false OR tc.isDeleted IS NULL) AND ti.category_id = :categoryId")
    List<TodoCombinedDTO> findCombinedTodoInfoByUserIdAAndCategoryId(@Param("userId") Long userId, @Param("categoryId") Long categoryId);

    /**
     * 更新指定用户和待办事项ID的待办事项状态。
     *
     * @param status 新的状态值。
     * @param todoId 待办事项的唯一标识符。
     * @param userId 用户的唯一标识符。
     * @return 被更新的记录数量。
     */
    @Modifying
    @Transactional
    @Query("UPDATE TodoInfo t SET t.status = :status WHERE t.id = :todoId AND t.user_id = :userId")
    int updateStatusByIdAndUserId(int status, @Param("userId") Long userId, @Param("todoId") Long todoId);
}

