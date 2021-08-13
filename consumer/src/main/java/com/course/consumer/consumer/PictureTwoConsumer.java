package com.course.consumer.consumer;

import com.course.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class PictureTwoConsumer {

    @Autowired
    private ObjectMapper objectMapper;

    private static final Logger LOG = LoggerFactory.getLogger(PictureTwoConsumer.class);

    @RabbitListener(queues = {"q.picture.image", "q.picture.vector", "q.picture.vector", "q.picture.filter"})
    public void listen(Message message) throws JsonProcessingException {
        String jsonString = new String(message.getBody());
        Picture picture = objectMapper.readValue(jsonString, Picture.class);

        LOG.info("consuming picture : {} with routing key : {} ", picture, message.getMessageProperties().getReceivedRoutingKey());
    }

}
