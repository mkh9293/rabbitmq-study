package com.example.rabbitmq;

import com.example.rabbitmq.entity.DummyMessage;
import com.example.rabbitmq.entity.InvoiceCancelledMessage;
import com.example.rabbitmq.entity.InvoiceCreatedMessage;
import com.example.rabbitmq.entity.InvoicePaidMessage;
import com.example.rabbitmq.producer.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Producer2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Producer2Application.class, args);
    }

    @Autowired
    private AnotherDummyProducer producer;
//    private InvoiceProducer invoiceProducer;
//    private ReliableProducer reliableProducer;
//    private SigleActiveProducer sigleActiveProducer;
//    private MultiplePrefechProducer multiplePrefechProducer;
//    private DummyProducer dummyProducer;

    @Override
    public void run(String... args) throws Exception {
        DummyMessage message = new DummyMessage("Just a dummy message", 1);
        producer.sendDummy(message);

//        for(int i=0; i<10; i++) {
//            String invoiceNumber = "INV-" + i;
//            InvoiceCancelledMessage message = new InvoiceCancelledMessage(LocalDate.now(), invoiceNumber, "Test " + i);
//            invoiceProducer.sendInvoiceCancelled(message);
//        }

//            String randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(800, 1000);
//        DummyMessage message1 = new DummyMessage("Invalid test", 10);
//        DummyMessage message2 = new DummyMessage("Invalid test", 20);
//
//        reliableProducer.sendDummyToInvalidExchange(message1);
//        reliableProducer.sendDummyWithInvalidRoutingKey(message2);

//        sigleActiveProducer.sendDummy();
//        System.out.println("Done");

//        for(int i=0; i<60; i++) {
//            String invoiceNumber = "INV-" + (i % 60);
//            InvoiceCreatedMessage message = new InvoiceCreatedMessage(ThreadLocalRandom.current().nextInt(200), LocalDate.now(), "USD", invoiceNumber);
//            invoiceProducer.sendInvoiceCreated(message);
//
//            String randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(800, 1000);
//            InvoicePaidMessage paymentMessage = new InvoicePaidMessage(invoiceNumber, LocalDate.now(), randomPaymentNumber);
//            invoiceProducer.sendInvoicePaid(paymentMessage);
//
//            InvoiceCancelledMessage cancelledMessage = new InvoiceCancelledMessage(LocalDate.now(), invoiceNumber, "Just Test");
//            invoiceProducer.sendInvoiceCancelled(cancelledMessage);
//        }

//        String randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(100, 200);
//        InvoiceCreatedMessage message = new InvoiceCreatedMessage(152.26, LocalDate.now().minusDays(2), "USD", randomInvoiceNumber);
//        invoiceProducer.sendInvoiceCreated(message);
//
//        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(200, 300);
//        String randomPaymentNumber = "PAY-" + ThreadLocalRandom.current().nextInt(800, 1000);
//        InvoicePaidMessage paymentMessage = new InvoicePaidMessage(randomInvoiceNumber, LocalDate.now(), randomPaymentNumber);
//        invoiceProducer.sendInvoicePaid(paymentMessage);
//
//        randomInvoiceNumber = "INV-" + ThreadLocalRandom.current().nextInt(300, 400);
//        InvoiceCancelledMessage cancelledMessage = new InvoiceCancelledMessage(LocalDate.now(), randomInvoiceNumber, "Just Test");
//        invoiceProducer.sendInvoiceCancelled(cancelledMessage);

//        multiplePrefechProducer.simulateScheduler();
//        multiplePrefechProducer.simulateTrasaction();
//
//        System.out.println("Done");

//        for(int i=0; i<500; i++) {
//            DummyMessage message = new DummyMessage("Now is " + LocalTime.now(), i);
//            dummyProducer.sendDummy(message);
//        }
//
//        System.out.println("All sent");
    }
}
