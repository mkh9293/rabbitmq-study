package com.course.producer;

import com.course.producer.entity.Picture;
import com.course.producer.producer.PictureProducer;
import com.course.producer.producer.PictureProducerTwo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

@SpringBootApplication
public class ProducerApplication implements CommandLineRunner {

    @Autowired
    private PictureProducerTwo pictureProducerTwo;

    private final List<String> SOURCES = List.of("mobile", "pc");
    private final List<String> TYPES = List.of("jpg", "png", "svg");

    public static void main(String[] args) {
        SpringApplication.run(ProducerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        for(int i=0; i<10; i++) {
            Picture picture = new Picture();
            picture.setName("Picture " + i);
            picture.setSize(ThreadLocalRandom.current().nextLong(1, 10000));
            picture.setSource(SOURCES.get(i % SOURCES.size()));
            picture.setType(TYPES.get(i % TYPES.size()));

            pictureProducerTwo.sendMessage(picture);
        }
    }
}
