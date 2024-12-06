/**
 * File Name: PatchUTContextEnum.java
 * Description: 定义用于表示待办事项更新操作上下文状态的枚举类。
 * 包含成功和未找到资源两种状态，用于服务层与控制器之间的通信，
 * 以便于根据不同的操作结果返回相应的HTTP响应。
 * Author: holic512
 * Created Date: 2024-12-06
 * Version: 1.0
 * Usage: 在待办事项更新操作中使用此枚举来表示操作的结果状态。
 */
package org.example.backend.user.todo.enums;

public enum PatchUTContextEnum {
    SUCCESS,
    NOT_FOUND,
}
