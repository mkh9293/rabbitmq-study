package com.course.producer.producer;

import com.course.producer.entity.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class RetryEmployeeProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ObjectMapper objectMapper;

    public void sendMessage(Employee employee) throws IOException {
        String json = objectMapper.writeValueAsString(employee);
        rabbitTemplate.convertAndSend("x.guideline2.work", "", json);
    }
}
