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
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GUTTodoInfoRep extends JpaRepository<TodoInfo, Long> {

    @Query("SELECT new org.example.backend.user.todo.dto.TodoCombinedDTO(" +
            "ti.id, ti.title, ti.description, ti.startDate, ti.dueDate, ti.status, ti.isDeleted, " +
            "tc.id, tc.name, tc.type) " +
            "FROM TodoInfo ti " +
            "LEFT JOIN TodoCategory tc ON ti.category_id = tc.id " +  // 改为 LEFT JOIN
            "WHERE ti.user_id = :userId AND (tc.isDeleted = false OR tc.isDeleted IS NULL)")
        // 处理没有分类的情况
    List<TodoCombinedDTO> findCombinedTodoInfoByUserId(@Param("userId") Long userId);

}

