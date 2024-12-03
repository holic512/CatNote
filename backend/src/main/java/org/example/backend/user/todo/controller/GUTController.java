/**
 * File Name: GUTController.java
 * Description: Todo
 * Author: holic512
 * Created Date: 2024-11-29
 * Version: 1.0
 * Usage:
 * Todo
 */
package org.example.backend.user.todo.controller;

import org.antlr.v4.runtime.misc.Pair;
import org.example.backend.common.util.StpKit;
import org.example.backend.user.todo.enums.GUTContextEnum;
import org.example.backend.user.todo.service.GUTService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("user/todo")
@RestController
public class GUTController {

    private final GUTService gutService;

    @Autowired
    public GUTController(GUTService gutService) {
        this.gutService = gutService;
    }

    /**
     * 获取 该用户的 全部todo - 对应前端的 全部
     *
     * @return
     */
    @GetMapping("/all")
    public Object getAllTodos() {
        // 获取用户Id
        Long UserId = (Long) StpKit.USER.getSession().get("id");

        // 调用服务类
        Pair<GUTContextEnum, Object> result = gutService.getAllTodos(UserId);

        return result.b;
    }


    /**
     * 获取 该用户的 未分类待做
     *
     * @return
     */
    @GetMapping("/uncategorized")
    public String getUncategorizedTodos() {
        return "Uncategorized todos";
    }

    /**
     * 获取 该用户的 某个分类的 待做
     *
     * @return
     */
    @GetMapping("/category/{category}")
    public String getTodosByCategory(String category) {
        return "Todos in category: " + category;
    }

}
