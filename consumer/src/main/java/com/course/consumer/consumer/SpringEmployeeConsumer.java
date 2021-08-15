package com.course.consumer.consumer;

import com.course.consumer.entity.Employee;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

//@Service
public class SpringEmployeeConsumer {

    private static final Logger log = LoggerFactory.getLogger(SpringEmployeeConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.spring2.accounting.work")
    public void listenAccounting(String messages) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(messages, Employee.class);
        log.info("On accounting, consuming {}", employee);

        if(StringUtils.isEmpty(employee.getName())) {
            throw new IllegalArgumentException("Name is empty");
        }

        log.info("On accounting, employee is {}", employee);
    }

    @RabbitListener(queues = "q.spring2.marketing.work")
    public void listenMarketing(String messages) throws JsonProcessingException {
        Employee employee = objectMapper.readValue(messages, Employee.class);
        log.info("On marketing, consuming {}", employee);

        log.info("On marketing, employee is {}", employee);
    }

}
