/**
 * File Name: TodoStatusEnum.java
 * Description: 定义待办事项的状态枚举，用于表示待办事项的不同状态。
 * Author: holic512
 * Created Date: 2024-12-06
 * Version: 1.0
 * Usage: 在系统中使用此枚举来表示待办事项的状态，确保状态管理的一致性。
 */
package org.example.backend.user.todo.enums;

import lombok.Getter;

@Getter
public enum TodoStatusEnum {

    /**
     * 未完成状态，表示待办事项尚未完成。
     */
    INCOMPLETE(0),

    /**
     * 已完成状态，表示待办事项已经完成。
     */
    COMPLETED(1);

    /**
     * -- GETTER --
     *  获取当前枚举成员对应的整数值。
     *
     * @return 整数值。
     */
    private final int value;

    TodoStatusEnum(int value) {
        this.value = value;
    }

}