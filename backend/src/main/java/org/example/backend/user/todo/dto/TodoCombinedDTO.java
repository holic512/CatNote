/**
 * File Name: TodoCombinedDTO.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-03
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.todo.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TodoCombinedDTO {
    // todoInfo 的信息
    private Long todo_id;
    private String title;
    private String description;
    private LocalDateTime startDate;  // 用于表示 "startDate"
    private LocalDateTime dueDate;    // 用于表示 "dueDate"
    private int status;               // 用于表示 "status"
    private boolean TodoInfoisDeleted;        // 用于表示 "TodoInfo的isDeleted"

    // todoCategory 的 信息
    private Long category_id;
    private String category_name;
    private Integer category_type;

    public TodoCombinedDTO(Long todo_id, String title, String description, LocalDateTime startDate, LocalDateTime dueDate, int status, boolean TodoInfoisDeleted,
                           Long category_id, String category_name, Integer category_type) {
        this.todo_id = todo_id;
        this.title = title;
        this.description = description;
        this.startDate = startDate;
        this.dueDate = dueDate;
        this.status = status;
        this.TodoInfoisDeleted = TodoInfoisDeleted;

        this.category_id = category_id;
        this.category_name = category_name;
        this.category_type = category_type;
    }


}

