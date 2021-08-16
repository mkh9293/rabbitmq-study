package com.example.rabbitmq.config;

import org.springframework.amqp.core.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqSchemaConfig {

//    @Bean
//    public FanoutExchange fanoutExchange() {
//        return new FanoutExchange("x.another-dummy", true, false);
//    }
//
//    @Bean
//    public Queue queueAnotherDummy() {
//        return new Queue("q.another-dummy");
//    }
//
//    @Bean
//    public DirectExchange directExchange() {
//        return new DirectExchange("test");
//    }

//    @Bean
//    public Binding bindingAnotherDummy() {
//        return new Binding("q.another-dummy", Binding.DestinationType.QUEUE, "x.another-dummy", null, null);

//        return BindingBuilder
//                .bind(queueAnotherDummy())
//                .to(directExchange())
//                .with("");

//        return BindingBuilder
//                .bind(queueAnotherDummy())
//                .to(fanoutExchange());
//    }

    @Bean
    public Declarables rabbitSchema() {
        return new Declarables(
                new FanoutExchange("x.another-dummy", true, false),
                new Queue("q.another-dummy"),
                new Binding("q.another-dummy", Binding.DestinationType.QUEUE, "x.another-dummy", "", null)
        );
    }

}
