package com.course.producer;

import com.course.producer.entity.Employee;
import com.course.producer.entity.Furniture;
import com.course.producer.entity.Picture;
import com.course.producer.producer.FurnitureProducer;
import com.course.producer.producer.MyPictureProducer;
import com.course.producer.producer.RetryEmployeeProducer;
import com.course.producer.producer.RetryPictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

    @Autowired
    private RetryEmployeeProducer retryEmployeeProducer;

//    private final List<String> SOURCES = List.of("mobile", "web");
//    private final List<String> TYPES = List.of("jpg", "png", "svg");

//    private final List<String> COLORS = List.of("white", "green", "red");
//    private final List<String> MATERIALS = List.of("wood", "plastic", "steel");

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<10; i++) {
            Employee employee = new Employee("emp-" + i, null, LocalDate.now());
            retryEmployeeProducer.sendMessage(employee);
        }
    }
}
