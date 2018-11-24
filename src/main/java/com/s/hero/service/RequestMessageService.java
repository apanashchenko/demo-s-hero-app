package com.s.hero.service;

import com.s.hero.model.RequestMessage;
import com.s.hero.repository.RequestMessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


/**
 * Created by alpa on 11/23/18
 */
@Service
@RequiredArgsConstructor
@Transactional
public class RequestMessageService {

    private final RequestMessageRepository requestMessageRepository;

    public Long add(RequestMessage requestMessage) {
        return requestMessageRepository.save(requestMessage).getId();
    }

    public Optional<RequestMessage> findById(Long id) {
        return requestMessageRepository.findById(id);
    }

    public Optional<RequestMessage> findByHeroIdAndStatus(Long id, String status) {
        return requestMessageRepository.findByHeroIdAndStatus(id, status);
    }


}
