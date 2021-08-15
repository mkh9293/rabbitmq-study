package com.course.consumer.consumer;

import com.course.consumer.entity.Picture;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//@Service
public class SpringPictureConsumer {

    private static final Logger log = LoggerFactory.getLogger(SpringPictureConsumer.class);

    @Autowired
    private ObjectMapper objectMapper;

    @RabbitListener(queues = "q.spring.image.work")
    public void listenImage(String messages) throws JsonProcessingException {
        Picture picture = objectMapper.readValue(messages, Picture.class);
        log.info("Consuming image {}", picture.getName());

        if(picture.getSize() > 9000) {
            throw new IllegalArgumentException("Image too large : " + picture.getName());
        }

        log.info("Processing image {}", picture.getName());
    }

    @RabbitListener(queues = "q.spring.vector.work")
    public void listenVector(String messages) throws JsonProcessingException {
        Picture picture = objectMapper.readValue(messages, Picture.class);
        log.info("Consuming vector {}", picture.getName());
        log.info("Processing vector {}", picture.getName());
    }
}
