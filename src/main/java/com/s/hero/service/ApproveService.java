package com.s.hero.service;

import com.s.hero.model.Hero;
import com.s.hero.model.RequestMessage;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Created by alpa on 11/23/18
 */
@Component
@Slf4j
public class ApproveService {

    public static final String HTTP_LOCALHOST_8090_RESPONSE = "http://localhost:8090/response";

    public void sendResponse(RequestMessage requestMessage) {
        int waitTime = getRandomSleep(10, 3);
        log.info("Sleep time {}", waitTime);
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(waitTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<RequestMessage> request = new HttpEntity<>(requestMessage);
        try {
            log.info("Send message request: " + requestMessage.toString());
            restTemplate.postForObject(HTTP_LOCALHOST_8090_RESPONSE, request, String.class);
        } catch (Exception e) {

        }
    }


    private int getRandomSleep(int maximum, int minimum) {
        Random rand = new Random();
        return rand.nextInt((maximum - minimum) + 1) + minimum;
    }
}
