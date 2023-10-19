package com.yupi.springbootinit.rabbitmq;

import com.yupi.springbootinit.constant.RabbitmqConstant;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class BiMessageProducer {

    @Resource
    private RabbitTemplate rabbitTemplate;

    /**
     * 发送消息
     * @param message
     */
    public void sendMessage(String message) {
        rabbitTemplate.convertAndSend(RabbitmqConstant.BI_EXCHANGE_NAME, RabbitmqConstant.BI_ROUTING_KEY, message);
    }

}