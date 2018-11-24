package com.s.hero;

import com.s.hero.model.Hero;
import com.s.hero.model.RequestMessage;
import com.s.hero.service.ApproveService;
import com.s.hero.service.HeroService;
import com.s.hero.service.RequestMessageService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Optional;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import static com.s.hero.MessageStatus.*;

/**
 * Created by alpa on 11/23/18
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ApproverScheduler {

    private final HeroService heroService;
    private final ApproveService approveService;
    private final RequestMessageService requestMessageService;


    @Scheduled(cron = "0/3 * * * * *")
    public void sendMessage() {
        Iterable<Hero> heroRequestList = heroService.findAll();
        heroRequestList.forEach(this::processRequest);
    }


    private void processRequest(Hero hero) {
        int waitTime = getRandomSleep(10, 3);
        log.info("Sleep time {}", waitTime);
        try {
            Thread.sleep(TimeUnit.SECONDS.toMillis(waitTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        switch (hero.getStatus()) {
            case REQUESTED:
                Optional<RequestMessage> requestMessage = requestMessageService.findByHeroIdAndStatus(hero.getId(), REQUESTED);
                if (!requestMessage.isPresent()) {
                    RequestMessage request = new RequestMessage();
                    request.setStatus(REQUESTED);
                    request.setHeroId(hero.getId());
                    request.setDescription("Person on review");

                    hero.setStatus(REQUESTED);
                    heroService.add(hero);
                    requestMessageService.add(request);
                    approveService.sendResponse(request);
                }
                break;
            case CANCELED:
                Optional<RequestMessage> cancelMessage = requestMessageService.findByHeroIdAndStatus(hero.getId(), CANCELED);
                if (!cancelMessage.isPresent()) {
                    RequestMessage cancel = new RequestMessage();
                    cancel.setStatus(CANCELED);
                    cancel.setDescription("Person canceled");
                    cancel.setHeroId(hero.getId());
                    hero.setStatus(CANCELED);
                    heroService.add(hero);
                    requestMessageService.add(cancel);
                    approveService.sendResponse(cancel);
                }
                break;
            case REJECTED:
                Optional<RequestMessage> rejectMessage = requestMessageService.findByHeroIdAndStatus(hero.getId(), REJECTED);
                if (!rejectMessage.isPresent()) {
                    RequestMessage reject = new RequestMessage();
                    reject.setStatus(REJECTED);
                    reject.setDescription("Person rejected");
                    reject.setHeroId(hero.getId());
                    hero.setStatus(REJECTED);
                    heroService.add(hero);
                    requestMessageService.add(reject);
                    approveService.sendResponse(reject);
                }
                break;
            case APPROVED:
                Optional<RequestMessage> approveMessage = requestMessageService.findByHeroIdAndStatus(hero.getId(), APPROVED);
                if (!approveMessage.isPresent()) {
                    RequestMessage approve = new RequestMessage();
                    approve.setStatus(APPROVED);
                    approve.setHeroId(hero.getId());
                    approve.setDescription("Person approved");

                    hero.setStatus(APPROVED);
                    heroService.add(hero);
                    requestMessageService.add(approve);
                    approveService.sendResponse(approve);
                }
                break;
            default:
                RequestMessage errorMessage = new RequestMessage();
                errorMessage.setStatus(hero.getStatus());
                errorMessage.setHeroId(hero.getId());

                approveService.sendResponse(errorMessage);

                break;
        }

    }


    private int getRandomSleep(int maximum, int minimum) {
        Random rand = new Random();
        return rand.nextInt((maximum - minimum) + 1) + minimum;
    }


}
