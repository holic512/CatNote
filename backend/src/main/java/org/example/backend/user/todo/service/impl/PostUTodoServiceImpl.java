/**
 * File Name: PostUTodoServiceImpl.java
 * Description: 服务实现类，用于处理用户待办事项分类（TodoCategory）的创建请求。
 *              提供了添加新分类的方法，并确保这些操作只影响属于指定用户的分类。
 * Author: holic512
 * Created Date: 2024-12-05
 * Version: 1.0
 * Usage: 在需要创建新的待办事项分类时使用本服务实现类。
 */
package org.example.backend.user.todo.service.impl;

import org.example.backend.common.entity.TodoCategory;
import org.example.backend.user.todo.enums.PostUTContextEnum;
import org.example.backend.user.todo.pojo.PostAddCategoryPojo;
import org.example.backend.user.todo.repository.UserTodoCategoryRep;
import org.example.backend.user.todo.service.PostUTodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostUTodoServiceImpl implements PostUTodoService {

    private final UserTodoCategoryRep gutTodoCategoryRep;

    @Autowired
    public PostUTodoServiceImpl(UserTodoCategoryRep userTodoCategoryRep) {
        this.gutTodoCategoryRep = userTodoCategoryRep;
    }

    @Override
    public PostUTContextEnum AddCategory(Long userId, PostAddCategoryPojo category) {
        try {
            // 一般情况下 因为有了用户才有对应的token 所以不会出现外键的 用户id为空的 清空

            // 实例化 TodoCategory
            TodoCategory todoCategory = new TodoCategory();
            todoCategory.setUser_id(userId);
            todoCategory.setName(category.getName());
            todoCategory.setType(category.getType());

            // 调用保存
            gutTodoCategoryRep.save(todoCategory);
            return PostUTContextEnum.SUCCESS;

        } catch (Exception e) {
            return null;
        }
    }
}
