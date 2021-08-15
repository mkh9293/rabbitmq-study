package com.example.rabbitmq;

import com.example.rabbitmq.entity.DummyMessage;
import com.example.rabbitmq.producer.DummyProducer;
import com.example.rabbitmq.producer.MultiplePrefechProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalTime;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class Producer2Application implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Producer2Application.class, args);
    }

    @Autowired
    private MultiplePrefechProducer multiplePrefechProducer;
//    private DummyProducer dummyProducer;

    @Override
    public void run(String... args) throws Exception {
        multiplePrefechProducer.simulateScheduler();
        multiplePrefechProducer.simulateTrasaction();

        System.out.println("Done");

//        for(int i=0; i<500; i++) {
//            DummyMessage message = new DummyMessage("Now is " + LocalTime.now(), i);
//            dummyProducer.sendDummy(message);
//        }
//
//        System.out.println("All sent");
    }
}
