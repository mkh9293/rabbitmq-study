package com.example.rabbitmq.producer;

import com.example.rabbitmq.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class DummyProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDummy(DummyMessage message) {
        rabbitTemplate.convertAndSend("x.dummy", "", message);
    }
}
