package com.course.producer.client;

import com.course.producer.entity.RabbitmqQueue;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.Duration;
import java.util.Base64;
import java.util.List;

@Service
public class RabbitmqClient {

    /**
     * ParameterizedTypeReference 클래스를 사용하는 이유.
     * 자바의 generic 정보는 컴파일 단계에서 제거되기 때문에 런타임 시 어떤 객체 타입인지 알수 없어서 에러가 발생한다.
     * reponse 가 generic 타입을 사용하는 경우 해당 클래스를 사용해야한다.
     */
    public List<RabbitmqQueue> getAllQueues() {
        WebClient webclient = WebClient.create("http://localhost:15672/api/queues");

        String basicAuthHeader = createBasicAuthHeader("guest", "guest");
        return webclient
                .get()
                .header(HttpHeaders.AUTHORIZATION, basicAuthHeader)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<RabbitmqQueue>>() {})
                .block(Duration.ofSeconds(10));

    }

    public String createBasicAuthHeader(String username, String password) {
        String authString = username + ":" + password;
        return "Basic " + Base64.getEncoder().encodeToString(authString.getBytes());
    }
}
