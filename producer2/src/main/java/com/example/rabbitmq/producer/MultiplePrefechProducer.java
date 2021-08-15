package com.example.rabbitmq.producer;

import com.example.rabbitmq.entity.DummyMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalTime;

@Service
public class MultiplePrefechProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void simulateTrasaction() {
        for(int i=0; i<20_000; i++) {
            DummyMessage message = new DummyMessage("Trasaction " + LocalTime.now(), i);
            rabbitTemplate.convertAndSend("x.transaction", "", message);
        }
    }

    public void simulateScheduler() {
        for(int i=0; i<200; i++) {
            DummyMessage message = new DummyMessage("Scheduler " + LocalTime.now(), i);
            rabbitTemplate.convertAndSend("x.scheduler", "", message);
        }
    }
}
