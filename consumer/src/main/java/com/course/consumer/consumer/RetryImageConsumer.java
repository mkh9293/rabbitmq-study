package com.course.consumer.consumer;

import com.course.consumer.entity.Picture;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rabbitmq.client.Channel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

import java.io.IOException;

//@Service
public class RetryImageConsumer {
    private static final String DEAD_EXCHANGE_NAME = "x.guideline.dead";
    private static final Logger log = LoggerFactory.getLogger(RetryImageConsumer.class);

    private DlxProcessingErrorHandler dlxProcessingErrorHandler;

    @Autowired
    private ObjectMapper objectMapper;

    public RetryImageConsumer() {
        dlxProcessingErrorHandler = new DlxProcessingErrorHandler(DEAD_EXCHANGE_NAME);
    }

    @RabbitListener(queues = "q.guideline.image.work")
    public void listen(Message message, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag) throws IOException {
        Picture picture = objectMapper.readValue(message.getBody(), Picture.class);

        try {
            if(picture.getSize() > 9000) {
                throw new IOException("Size too large");
            } else {
                log.info("Creating thumbnail & publishing : " + picture);
                channel.basicAck(deliveryTag, false);
            }
        } catch (IOException e) {
            log.warn("Error processing message " + new String(message.getBody()) + " : " + e.getMessage());
            dlxProcessingErrorHandler.handleErrorProcessingMessage(message, channel, deliveryTag);
        }
    }
}
