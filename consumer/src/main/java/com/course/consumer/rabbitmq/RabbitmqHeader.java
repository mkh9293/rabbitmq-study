package com.course.consumer.rabbitmq;

import org.apache.commons.lang3.StringUtils;

import java.util.*;

public class RabbitmqHeader {
    private static final String KEYWORD_QUEUE_WAIT = "wait";
    private List<RabbitmqHeaderXDeath> xDeathList = new ArrayList<>(2);
    private String xFirstDeathExchange = StringUtils.EMPTY;
    private String xFirstDeathQueue = StringUtils.EMPTY;
    private String xFirstDeathReason = StringUtils.EMPTY;

    public RabbitmqHeader(Map<String, Object> headers) {
        if(headers != null) {
            Optional<Object> xFirstDeathExchange = Optional.ofNullable(headers.get("x-first-death-exchange"));
            Optional<Object> xFirstDeathQueue = Optional.ofNullable(headers.get("x-first-death-queue"));
            Optional<Object> xFirstDeathReason = Optional.ofNullable(headers.get("x-first-death-reason"));

            xFirstDeathExchange.ifPresent(s -> this.setxFirstDeathExchange(s.toString()));
            xFirstDeathQueue.ifPresent(s -> this.setxFirstDeathQueue(s.toString()));
            xFirstDeathReason.ifPresent(s -> this.setxFirstDeathReason(s.toString()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) headers.get("x-death");

            if(list != null) {
                for(Map<String, Object> x : list) {
                    RabbitmqHeaderXDeath xDeath = new RabbitmqHeaderXDeath();
                    Optional.ofNullable(x.get("reason")).ifPresent(s -> xDeath.setReason(s.toString()));
                    Optional.ofNullable(x.get("count")).ifPresent(s -> xDeath.setCount(Integer.parseInt(s.toString())));
                    Optional.ofNullable(x.get("exchange")).ifPresent(s -> xDeath.setExchange(s.toString()));
                    Optional.ofNullable(x.get("queue")).ifPresent(s -> xDeath.setQueue(s.toString()));
                    Optional.ofNullable(x.get("routing-keys")).ifPresent(s -> {
                        List listR = (List<String>) s;
                        xDeath.setRoutingKeys(listR);
                    });
                    Optional.ofNullable(x.get("time")).ifPresent(s -> xDeath.setTime((Date) s));

                    xDeathList.add(xDeath);
                }
            }
        }
    }

    public int getFailedRetryCount() {
        for(RabbitmqHeaderXDeath xDeaths : xDeathList) {
            if(xDeaths.getExchange().toLowerCase().endsWith(KEYWORD_QUEUE_WAIT) && xDeaths.getQueue().toLowerCase().endsWith(KEYWORD_QUEUE_WAIT)) {
                return xDeaths.getCount();
            }
        }

        return 0;
    }

    public List<RabbitmqHeaderXDeath> getxDeathList() {
        return xDeathList;
    }

    public void setxDeathList(List<RabbitmqHeaderXDeath> xDeathList) {
        this.xDeathList = xDeathList;
    }

    public String getxFirstDeathExchange() {
        return xFirstDeathExchange;
    }

    public void setxFirstDeathExchange(String xFirstDeathExchange) {
        this.xFirstDeathExchange = xFirstDeathExchange;
    }

    public String getxFirstDeathQueue() {
        return xFirstDeathQueue;
    }

    public void setxFirstDeathQueue(String xFirstDeathQueue) {
        this.xFirstDeathQueue = xFirstDeathQueue;
    }

    public String getxFirstDeathReason() {
        return xFirstDeathReason;
    }

    public void setxFirstDeathReason(String xFirstDeathReason) {
        this.xFirstDeathReason = xFirstDeathReason;
    }
}
