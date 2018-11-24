package com.s.hero.service;

import com.s.hero.model.Hero;
import com.s.hero.model.RequestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Created by alpa on 11/23/18
 */
@Component
@Slf4j
public class ApproveService {

    public static final String HTTP_LOCALHOST_8081_RANDOM = "http://localhost:8081/random";

    public void sendResponse(Hero hero) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Hero> request = new HttpEntity<>(hero);
        try {
            log.info("Send approve response");
            restTemplate.postForObject(HTTP_LOCALHOST_8081_RANDOM, request, String.class);
        } catch (Exception e) {

        }
    }

    public void sendResponse(RequestMessage requestMessage) {
        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RequestMessage> request = new HttpEntity<>(requestMessage);
        try {
            log.info("Send message request: " + requestMessage.toString());
            restTemplate.postForObject(HTTP_LOCALHOST_8081_RANDOM, request, String.class);
        } catch (Exception e) {

        }
    }
}
