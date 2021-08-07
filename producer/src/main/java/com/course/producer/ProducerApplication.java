package com.course.producer;

import com.course.producer.entity.Furniture;
import com.course.producer.entity.Picture;
import com.course.producer.producer.FurnitureProducer;
import com.course.producer.producer.MyPictureProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

    @Autowired
    private MyPictureProducer myPictureProducer;

    private final List<String> SOURCES = List.of("mobile", "web");
    private final List<String> TYPES = List.of("jpg", "png", "svg");

//    private final List<String> COLORS = List.of("white", "green", "red");
//    private final List<String> MATERIALS = List.of("wood", "plastic", "steel");

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<1; i++) {
            Picture picture = new Picture();
            picture.setName("Picture " + i);
            picture.setSize(ThreadLocalRandom.current().nextLong(9000 , 10000));
            picture.setSource(SOURCES.get(i % SOURCES.size()));
            picture.setType(TYPES.get(i % TYPES.size()));

            myPictureProducer.sendMessage(picture);
        }
    }
}
