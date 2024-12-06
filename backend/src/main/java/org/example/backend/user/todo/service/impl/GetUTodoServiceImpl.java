/**
 * File Name: GetUTodoServiceImpl.java
 * Description: 服务实现类，用于处理获取用户待办事项（Todos）及其分类（Categories）的业务逻辑。
 *              提供了根据用户ID获取所有待办事项、所有分类以及特定分类下的所有待办事项的方法。
 * Author: holic512
 * Created Date: 2024-12-02
 * Version: 1.0
 * Usage: 在需要从数据库中检索用户待办事项或分类信息时使用本服务实现类。
 */
package org.example.backend.user.todo.service.impl;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.entity.TodoCategory;
import org.example.backend.user.todo.dto.TodoCombinedDTO;
import org.example.backend.user.todo.enums.GetUTContextEnum;
import org.example.backend.user.todo.repository.UserTodoCategoryRep;
import org.example.backend.user.todo.repository.UserTodoInfoRep;
import org.example.backend.user.todo.service.GetUTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetUTodoServiceImpl implements GetUTodoService {

    private final UserTodoCategoryRep gutTodoCategoryRep;
    private final UserTodoInfoRep userTodoInfoRep;

    @Autowired
    public GetUTodoServiceImpl(UserTodoCategoryRep userTodoCategoryRep,
                               UserTodoInfoRep userTodoInfoRep) {
        this.gutTodoCategoryRep = userTodoCategoryRep;
        this.userTodoInfoRep = userTodoInfoRep;
    }

    @Override
    public Pair<GetUTContextEnum, Object> getAllTodos(Long userId) {
        // 获取 该用户名下的 全部 TodosCombined
        List<TodoCombinedDTO> TodosCombined = userTodoInfoRep.findCombinedTodoInfoByUserId(userId);
        return new Pair<>(GetUTContextEnum.SUCCESS, TodosCombined);
    }

    @Override
    public Pair<GetUTContextEnum, Object> getAllTodoCategory(Long userId) {
        // 获取 该用户名下的 全部 TodoTree

        List<TodoCategory> todoCategories = gutTodoCategoryRep.findAllByUserId(userId);
        return new Pair<>(GetUTContextEnum.SUCCESS, todoCategories);
    }

    @Override
    public Pair<GetUTContextEnum, Object> getAllTodosByCategoryId(Long userId, Long categoryId) {
        List<TodoCombinedDTO> todoCombinedDTOS = userTodoInfoRep.findCombinedTodoInfoByUserIdAAndCategoryId(userId, categoryId);

        return new Pair<>(GetUTContextEnum.SUCCESS, todoCombinedDTOS);
    }

}
