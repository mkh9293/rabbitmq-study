package com.example.rabbitmq.producer;

import com.example.rabbitmq.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SigleActiveProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendDummy() {
        for(int i=0; i< 10_000; i++) {
            DummyMessage message = new DummyMessage("Message " + i, i);
            rabbitTemplate.convertAndSend("x.single", "", message);
        }
    }

}
