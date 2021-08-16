package com.example.rabbitmq.producer;

import com.example.rabbitmq.entity.InvoiceCancelledMessage;
import com.example.rabbitmq.entity.InvoiceCreatedMessage;
import com.example.rabbitmq.entity.InvoicePaidMessage;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceProducer {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    private static final String EXCHANGE = "x.invoice";
//    private static final String EXCHANGE = "x.invoice.cancel";

    public void sendInvoiceCreated(InvoiceCreatedMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
    }

    public void sendInvoicePaid(InvoicePaidMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
    }

    public void sendInvoiceCancelled(InvoiceCancelledMessage message) {
        rabbitTemplate.convertAndSend(EXCHANGE, message.getInvoiceNumber(), message);
    }

}
