package com.example.rabbitmq.consumer;

import com.example.rabbitmq.entity.DummyMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

//@Service
public class MultiplePrefechConsumer {

    private static final Logger LOG = LoggerFactory.getLogger(MultiplePrefechConsumer.class);

    @RabbitListener(queues = "q.transaction", concurrency = "2")
    public void listenTrasaction(DummyMessage dummyMessage) throws InterruptedException {
        LOG.info("Taking Transaction {}", dummyMessage);
        TimeUnit.MILLISECONDS.sleep(100);
    }

    @RabbitListener(queues = "q.scheduler", concurrency = "2", containerFactory = "prefetchOneContainerFactory")
    public void listenScheduler(DummyMessage dummyMessage) throws InterruptedException {
        LOG.info("Taking Scheduler {}", dummyMessage);
        TimeUnit.SECONDS.sleep(1);
    }
}
