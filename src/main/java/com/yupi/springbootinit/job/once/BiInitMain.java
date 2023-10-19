package com.yupi.springbootinit.job.once;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.yupi.springbootinit.common.ErrorCode;
import com.yupi.springbootinit.constant.RabbitmqConstant;
import com.yupi.springbootinit.exception.BusinessException;

import java.util.HashMap;
import java.util.Map;

/**
 * 用于创建测试程序用到的交换机和队列（只用在程序启动前执行一次）
 */
public class BiInitMain {

    public static void main(String[] args) {
        try {
            ConnectionFactory factory = new ConnectionFactory();
            factory.setHost("106.55.250.250");
            factory.setUsername("guest");
            factory.setPassword("guest");
            factory.setPort(5672);
            factory.setVirtualHost("/");
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel();
            String EXCHANGE_NAME =  RabbitmqConstant.BI_EXCHANGE_NAME;
            String EXCHANGE_NAME_DEAD =  RabbitmqConstant.BI_EXCHANGE_DEAD;
            channel.exchangeDeclare(EXCHANGE_NAME, "direct");
            channel.exchangeDeclare(EXCHANGE_NAME_DEAD, "direct");
            // 创建队列，随机分配一个队列名称
            String queueName = RabbitmqConstant.BI_QUEUE_NAME;

            // 通过设置 x-message-ttl 参数来指定消息的过期时间
            Map<String, Object> queueArgs = new HashMap<>();
            queueArgs.put("x-message-ttl", 60000); // 过期时间为 60 秒
            // 参数解释：queueDeclare(String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments)
            // durable: 持久化队列（重启后依然存在）
            // exclusive: 排他性队列（仅限此连接可见，连接关闭后队列删除）
            // autoDelete: 自动删除队列（无消费者时自动删除）
            channel.queueDeclare(queueName, true, false, false, queueArgs);

            String deadLetterRoutingKey = ""; // 空字符串，表示所有过期消息都会路由到死信交换机
            Map<String, Object> deadArgs = new HashMap<>();
            deadArgs.put("x-dead-letter-exchange", EXCHANGE_NAME_DEAD);
            deadArgs.put("x-dead-letter-routing-key", deadLetterRoutingKey);
            // 将队列与交换机进行绑定
            channel.queueBind(queueName, EXCHANGE_NAME, RabbitmqConstant.BI_ROUTING_KEY,deadArgs);
            // 创建一个死信队列
            String queueDeadName = RabbitmqConstant.BI_QUEUE_DEAD_NAME;
            // 声明私信队列，并将其绑定到私信交换机。
            channel.queueDeclare(queueDeadName,true,false,false,null);
            channel.queueBind(queueDeadName,EXCHANGE_NAME_DEAD,"");
        } catch (Exception e) {
            throw new BusinessException(ErrorCode.SYSTEM_ERROR);
        }

    }
}
