package com.example.rabbitmq.consumer;

import com.example.rabbitmq.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

//@Service
public class DummyConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyConsumer.class);

    @RabbitListener(queues = "q.dummy")
    public void listenDummy(DummyMessage dummyMessage) {
        LOG.info("Message is {}", dummyMessage);
    }



}
