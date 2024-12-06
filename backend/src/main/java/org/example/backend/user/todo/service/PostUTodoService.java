/**
 * File Name: PostUTodoService.java
 * Description: 定义了用于处理用户待办事项分类（TodoCategory）创建请求的服务接口。
 *              该接口规定了添加新分类的方法签名，确保服务层的实现遵循统一的标准。
 * Author: holic512
 * Created Date: 2024-12-05
 * Version: 1.0
 * Usage: 在需要定义待办事项分类创建逻辑时使用本接口。具体实现类将提供实际的业务逻辑处理。
 */
package org.example.backend.user.todo.service;

import org.example.backend.user.todo.enums.PostUTContextEnum;
import org.example.backend.user.todo.pojo.PostAddCategoryPojo;

public interface PostUTodoService {

    /**
     * 添加新的待办事项分类。
     *
     * @param userId   用户唯一标识符，用于确定分类所属的用户。
     * @param category 包含待办事项分类信息的数据传输对象（DTO），用于传递分类名称和类型等信息。
     * @return 操作结果枚举，表示操作是否成功或失败原因。
     */
    PostUTContextEnum AddCategory(Long userId, PostAddCategoryPojo category);
}