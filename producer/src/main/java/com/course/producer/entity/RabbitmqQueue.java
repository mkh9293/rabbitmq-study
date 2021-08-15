package com.course.producer.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true) // json 객체 매핑 시, 현재 클래스의 정의된 속성외의 다른 속성이 존재할 경우 에러 발생하는 것을 방지
public class RabbitmqQueue {

    private long messages;

    private String name;

    public boolean isDirty() {
        return messages > 0;
    }

    public long getMessages() {
        return messages;
    }

    public void setMessages(long messages) {
        this.messages = messages;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "RabbitmqQueue{" +
                "messages=" + messages +
                ", name='" + name + '\'' +
                '}';
    }
}
