/**
 * File Name: MQRoutingKey.java
 * Description: rabbitMQ 队列名称 枚举
 * Author: holic512
 * Created Date: 2024-09-10
 * Version: 1.0
 * Usage:
 * 在定义 消息队列名称时 使用此枚举已到达 约束规范的目的
 */
package org.example.backend.common.rabbitMQ.enums;

public enum MQRoutingKey {
    // 定义
    EMAIL_ROUTING_KEY("emailRoutingKey");

    // 操作
    private final String key;

    MQRoutingKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
