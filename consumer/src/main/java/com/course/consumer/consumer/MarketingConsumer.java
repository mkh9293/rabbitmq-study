package com.course.consumer.consumer;

import com.course.consumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class MarketingConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger LOG = LoggerFactory.getLogger(MarketingConsumer.class);

    @RabbitListener(queues = "q.hr.marketing")
    public void listen(String message) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(message, Employee.class);

        LOG.info("employee on marketing is {}", employee);
    }
}
