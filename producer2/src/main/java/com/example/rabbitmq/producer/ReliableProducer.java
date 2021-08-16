package com.example.rabbitmq.producer;

import com.example.rabbitmq.entity.DummyMessage;
import org.apache.commons.logging.Log;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
public class ReliableProducer {

    private static final Logger LOG = LoggerFactory.getLogger(ReliableProducer.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void registerCallback() {
        rabbitTemplate.setConfirmCallback((correlationData, ack, cause) -> {
            if(correlationData == null) {
                return;
            }

            if(ack) {
                LOG.info("Message with correlation {} published", correlationData.getId());
            } else {
                LOG.info("Invalid exchange for message with correlation {} published", correlationData.getId());
            }
        });

        rabbitTemplate.setReturnsCallback(returned -> {
            LOG.info("return callback");

            if(returned.getReplyText() != null && returned.getReplyText().equalsIgnoreCase("NO_ROUTE")) {
                String id = returned.getMessage().getMessageProperties().getHeader("spring_returned_message_correlation").toString();
                LOG.warn("Invalid routing key for message {}", id);
            }
        });
    }

    public void sendDummyWithInvalidRoutingKey(DummyMessage message) {
        CorrelationData correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.dummy2", "invalid-routing-key", message, correlationData);
    }

    public void sendDummyToInvalidExchange(DummyMessage message) {
        CorrelationData correlationData = new CorrelationData(Integer.toString(message.getPublishOrder()));
        rabbitTemplate.convertAndSend("x.non-exist-exchange", "", message, correlationData);
    }

}
