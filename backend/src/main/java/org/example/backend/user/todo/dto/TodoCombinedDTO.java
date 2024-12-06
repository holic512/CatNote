/**
 * 文件名：TodoCombinedDTO.java
 * <p>
 * 描述：
 * 此类是一个综合的数据传输对象（DTO），用于封装待办事项（TodoInfo）的详细信息及其关联的分类信息。
 * 它专为数据层查询设计，以便在应用程序的不同层之间方便地传输完整的待办事项数据。
 * <p>
 * 作者：holic512
 * 创建日期：2024-12-03
 * 版本：1.0
 * <p>
 * 使用说明：
 * 此DTO可以在需要合并待办事项和分类数据的各种场景中使用，例如，在数据访问层（DAO）或服务层中查询和返回综合的待办事项信息。
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

