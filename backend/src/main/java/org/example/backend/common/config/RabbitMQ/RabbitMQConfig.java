/**
 * File Name: RabbitMQConfig.java
 * Description: rabbitMQ的配置类
 * Author: holic512
 * Created Date: 2024-09-10
 * Version: 1.0
 * Usage:
 * 用来配置rabbitMQ的配置,其他类可以通过rabbitTemplate来调用
 */
package org.example.backend.common.config.RabbitMQ;

import io.github.cdimascio.dotenv.Dotenv;
import org.example.backend.common.enums.MQExchangeType;
import org.example.backend.common.enums.MQQueueName;
import org.example.backend.common.enums.MQRoutingKey;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    private final Dotenv dotenv = Dotenv.configure().directory("backend/.env").load();


    // 定义交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(MQExchangeType.DIRECT_EXCHANGE.getValue());
    }

    // 配置连接工厂，使用 .env 中的配置
    @Bean
    public ConnectionFactory connectionFactory() {
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(dotenv.get("RABBITMQ_HOST"));
        connectionFactory.setPort(Integer.parseInt(dotenv.get("RABBITMQ_PORT")));
        connectionFactory.setUsername(dotenv.get("RABBITMQ_USERNAME"));
        connectionFactory.setPassword(dotenv.get("RABBITMQ_PASSWORD"));
        return connectionFactory;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL); // 启用手动应答
        // 如果需要，还可以设置其他属性，如并发消费者数等
        return factory;
    }


    // 邮件队列
    @Bean
    public Queue emailQueue() {
        // Map<String, Object> args = new HashMap<>();
        // // 设置消息的 TTL 为 10 分钟 (600000 毫秒)
        // args.put("x-message-ttl", 600000);
        return new Queue(MQQueueName.EMAIL_QUEUE.getValue(), true, false, false);
    }

    // 将邮件队列与交换机绑定
    @Bean
    public Binding emailBinding(Queue emailQueue, DirectExchange directExchange) {
        return BindingBuilder.bind(emailQueue).to(directExchange).with(MQRoutingKey.EMAIL_ROUTING_KEY.getKey());
    }


}
