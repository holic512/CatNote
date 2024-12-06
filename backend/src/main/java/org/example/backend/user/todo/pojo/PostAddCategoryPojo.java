/**
 * File Name: PostAddCategoryPojo.java
 * Description: 数据传输对象（DTO），用于封装待办事项分类（文件夹）创建请求的数据。
 *              该类主要用于从客户端接收创建新分类所需的信息，并传递给服务层进行处理。
 * Author: holic512
 * Created Date: 2024-12-05
 * Version: 1.0
 * Usage: 在创建新的待办事项分类时使用此POJO来携带必要的信息。
 */
package org.example.backend.user.todo.pojo;

import lombok.Data;
import lombok.NonNull;

@Data
public class PostAddCategoryPojo {
    @NonNull
    private String name;
    private int type;

    public PostAddCategoryPojo() {
    }

    public PostAddCategoryPojo(@NonNull String name, int type) {
        this.name = name;
        this.type = type;
    }
}
