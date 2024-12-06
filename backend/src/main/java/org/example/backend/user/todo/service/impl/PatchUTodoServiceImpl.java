/**
 * File Name: PatchUTodoServiceImpl.java
 * Description: 服务实现类，用于处理用户待办事项（Todos）的状态更新请求。
 * 提供了完成和重新打开待办事项的方法，并确保这些操作只影响属于指定用户的待办事项。
 * Author: holic512
 * Created Date: 2024-12-06
 * Version: 1.0
 * Usage: 在需要更新用户待办事项状态时使用本服务实现类。
 */
package org.example.backend.user.todo.service.impl;

import org.example.backend.user.todo.enums.PatchUTContextEnum;
import org.example.backend.user.todo.enums.TodoStatusEnum;
import org.example.backend.user.todo.repository.UserTodoInfoRep;
import org.example.backend.user.todo.service.PatchUTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PatchUTodoServiceImpl implements PatchUTodoService {
    private final UserTodoInfoRep userTodoInfoRep;

    @Autowired
    public PatchUTodoServiceImpl(UserTodoInfoRep userTodoInfoRep) {
        this.userTodoInfoRep = userTodoInfoRep;
    }

    @Override
    public PatchUTContextEnum completeTodo(Long UserId, Long todoId) {
        // 逻辑在于 保证完成的 待做任务属于 UserId
        int updatedCount = userTodoInfoRep.updateStatusByIdAndUserId(TodoStatusEnum.COMPLETED.getValue(), UserId, todoId);
        if (updatedCount > 0) {
            return PatchUTContextEnum.SUCCESS;
        } else {
            return PatchUTContextEnum.NOT_FOUND; // 或者其他适当的错误码
        }
    }

    @Override
    public PatchUTContextEnum reopenTodo(Long UserId, Long todoId) {
        int updatedCount = userTodoInfoRep.updateStatusByIdAndUserId(TodoStatusEnum.INCOMPLETE.getValue(), UserId, todoId);
        if (updatedCount > 0) {
            return PatchUTContextEnum.SUCCESS;
        } else {
            return PatchUTContextEnum.NOT_FOUND; // 或者其他适当的错误码
        }
    }
}
