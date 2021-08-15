package com.course.producer.scheduler;

import com.course.producer.client.RabbitmqClient;
import com.course.producer.entity.RabbitmqQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

//@Service
public class RabbitmqScheduler {

    public static final Logger LOG = LoggerFactory.getLogger(RabbitmqScheduler.class);

    @Autowired
    private RabbitmqClient client;

    @Scheduled(fixedDelay = 90000) // fixedDelay 는 해당 작업 끝난 시간 + delay 정의된 시간
    public void sweepDirtyQueues() {
        try {
            List<RabbitmqQueue> dirtyQueues = client.getAllQueues()
                    .stream()
                    .filter(RabbitmqQueue::isDirty)
                    .collect(Collectors.toList());

            dirtyQueues.forEach(q -> LOG.info("Queue {} has {} unprocessed messages", q.getName(), q.getMessages()));

        } catch (Exception e) {
            LOG.warn("Cannot sweep queue : {}", e.getMessage());
        }
    }



}
