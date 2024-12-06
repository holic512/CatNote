/**
 * File Name: PatchUTController.java
 * Description: 控制器类，用于处理用户待办事项（Todos）的更新请求，包括完成和重新打开待办事项。
 * Author: holic512
 * Created Date: 2024-12-05
 * Version: 1.0
 * Usage: 通过 HTTP PATCH 请求来调用本控制器的方法，以实现对用户待办事项状态的更新操作。
 */
package org.example.backend.user.todo.controller;

import org.example.backend.common.response.ApiResponse;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.todo.enums.PatchUTContextEnum;
import org.example.backend.user.todo.service.PatchUTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user/todo")
public class PatchUTController {

    private final PatchUTodoService patchUTodoService;

    @Autowired
    public PatchUTController(PatchUTodoService patchUTodoService) {
        this.patchUTodoService = patchUTodoService;
    }


    @PatchMapping("/complete")
    public ResponseEntity<ApiResponse<Object>> completeTodo(@Param("todoId") Long todoId) {
        // 获取用户Id
        Long UserId = (Long) StpKit.USER.getSession().get("id");

        // 执行服务层
        PatchUTContextEnum result = patchUTodoService.completeTodo(UserId, todoId);

        if (result == PatchUTContextEnum.SUCCESS) {
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("更新成功")
                    .build()
            );
        }
        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(500)
                .message("更新失败")
                .build()
        );
    }

    @PatchMapping("/reopen")
    public ResponseEntity<ApiResponse<Object>> reopenTodo(@Param("todoId") Long todoId) {
        // 获取用户Id
        Long UserId = (Long) StpKit.USER.getSession().get("id");

        // 执行服务层
        PatchUTContextEnum result = patchUTodoService.reopenTodo(UserId, todoId);

        if (result == PatchUTContextEnum.SUCCESS) {
            return ResponseEntity.ok(new ApiResponse.Builder<>()
                    .status(200)
                    .message("更新成功")
                    .build()
            );
        }
        return ResponseEntity.ok(new ApiResponse.Builder<>()
                .status(500)
                .message("更新失败")
                .build()
        );
    }

}
