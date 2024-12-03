/**
 * File Name: GUTServiceImpl.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-02
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.todo.service.impl;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.entity.TodoCategory;
import org.example.backend.common.entity.TodoInfo;
import org.example.backend.user.todo.dto.TodoCombinedDTO;
import org.example.backend.user.todo.enums.GUTContextEnum;
import org.example.backend.user.todo.repository.GUTTodoCategoryRep;
import org.example.backend.user.todo.repository.GUTTodoInfoRep;
import org.example.backend.user.todo.service.GUTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GUTServiceImpl implements GUTService {

    private final GUTTodoCategoryRep gutTodoCategoryRep;
    private final GUTTodoInfoRep gutTodoInfoRep;

    @Autowired
    public GUTServiceImpl(GUTTodoCategoryRep gutTodoCategoryRep,
                          GUTTodoInfoRep gutTodoInfoRep) {
        this.gutTodoCategoryRep = gutTodoCategoryRep;
        this.gutTodoInfoRep = gutTodoInfoRep;
    }

    @Override
    public Pair<GUTContextEnum, Object> getAllTodos(Long userId) {
        // 获取 该用户名下的 全部 TodosCombined
        List<TodoCombinedDTO> TodosCombined = gutTodoInfoRep.findCombinedTodoInfoByUserId(userId);
        return new Pair<>(GUTContextEnum.SUCCESS, TodosCombined);
    }

}
