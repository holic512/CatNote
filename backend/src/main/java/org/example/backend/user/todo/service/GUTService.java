/**
 * File Name: GUTService.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-12-02
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.todo.service;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.user.todo.enums.GUTContextEnum;
import org.springframework.stereotype.Service;


public interface GUTService {

    Pair<GUTContextEnum, Object> getAllTodos(Long userId);
}
