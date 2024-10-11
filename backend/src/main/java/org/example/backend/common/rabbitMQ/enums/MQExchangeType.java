/**
 * File Name: MQExchangeType.java
 * Description: rabbitMQ 交换机 枚举
 * Author: holic512
 * Created Date: 2024-09-10
 * Version: 1.0
 * Usage:
 * 在定义 消息队列交换机时 使用此枚举,已到达约束规范的目的
 */
package org.example.backend.common.rabbitMQ.enums;

public enum MQExchangeType {
    // 枚举定义
    DIRECT_EXCHANGE("directExchange");


    // 操作
    private final String value;

    MQExchangeType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
