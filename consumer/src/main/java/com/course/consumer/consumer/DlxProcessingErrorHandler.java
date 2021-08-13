package com.course.consumer.consumer;

import com.course.consumer.rabbitmq.RabbitmqHeader;
import com.rabbitmq.client.Channel;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.lang.NonNull;

import java.io.IOException;
import java.time.LocalDateTime;

public class DlxProcessingErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(DlxProcessingErrorHandler.class);

    @NonNull
    private String deadExchangeName;

    private int maxRetryCount = 3;

    public DlxProcessingErrorHandler(String deadExchangeName) throws IllegalArgumentException {
        if(StringUtils.isAnyEmpty(deadExchangeName)) {
            throw new IllegalArgumentException("Must define dlx exchange name");
        }

        this.deadExchangeName = deadExchangeName;
    }

    public boolean handleErrorProcessingMessage(Message message, Channel channel, long deliveryTag) throws IOException {
        RabbitmqHeader header = new RabbitmqHeader(message.getMessageProperties().getHeaders());

        try {
            if(header.getFailedRetryCount() >= maxRetryCount) {
                channel.basicPublish(getDeadExchangeName(), message.getMessageProperties().getReceivedRoutingKey(), null, message.getBody());
                channel.basicAck(deliveryTag, false);
            } else {
                channel.basicReject(deliveryTag, false);
            }

            return true;
        } catch (IOException e) {
            log.warn("[HANDLER-FAILED] Error at " + LocalDateTime.now() + " on retry "
                    + header.getFailedRetryCount() + " for message " + new String(message.getBody()));
        }

        return false;
    }

    @NonNull
    public String getDeadExchangeName() {
        return deadExchangeName;
    }
}
