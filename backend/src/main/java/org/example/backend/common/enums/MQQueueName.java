/**
 * File Name: MQQueueName.java
 * Description: 定义了 RabbitMQ 中所有队列的名称。通过使用枚举来管理队列名称，可以避免硬编码字符串，减少出错的可能性。
 * Author: holic512
 * Created Date: 2024-09-10
 * Version: 1.0
 * Usage:
 * 在 Spring Boot 配置和 RabbitMQ 消息发送逻辑中使用枚举值来引用队列名称。
 * 例如，在配置队列时可以使用 `QueueName.EMAIL_QUEUE.getValue()` 来获取队列名称。
 * 这种方式可以提高代码的可维护性和可读性。
 */
package org.example.backend.common.enums;

public enum MQQueueName {
    // 定义枚举
    EMAIL_QUEUE("emailQueue");

    // 操作
    private final String value;

    MQQueueName(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}


