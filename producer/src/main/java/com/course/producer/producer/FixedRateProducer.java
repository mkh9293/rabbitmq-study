package com.course.producer.producer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

//@Service
public class FixedRateProducer {

    @Autowired
    public RabbitTemplate rabbitTemplate;

    private int i = 0;

    public static final Logger LOG = LoggerFactory.getLogger(FixedRateProducer.class);

    @Scheduled(fixedRate = 500)
    public void sendMassage() {
        i++;
        LOG.info("i is {}", i);
        rabbitTemplate.convertAndSend("course.fixedrate", "fixed rate " + i);
    }
}
