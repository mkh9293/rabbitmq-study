package com.example.rabbitmq.consumer;

import com.example.rabbitmq.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class DummyPrefechConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(DummyPrefechConsumer.class);

    @RabbitListener(queues = "q.dummy", concurrency = "2")
    public void listenDummy(DummyMessage dummyMessage) throws InterruptedException {
        LOG.info("Message is {}", dummyMessage);
        TimeUnit.SECONDS.sleep(20);
    }
}
