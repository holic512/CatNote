/**
 * File Name: GUTTodoCategoryRep.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-03
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.todo.repository;

import org.example.backend.common.entity.TodoCategory;
import org.example.backend.common.entity.TodoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GUTTodoCategoryRep extends JpaRepository<TodoCategory, Long> {

    @Query("SELECT tc FROM TodoCategory tc where tc.user_id = :userId")
    List<TodoCategory> findAllByUserId(@Param("userId") Long userId);
}
