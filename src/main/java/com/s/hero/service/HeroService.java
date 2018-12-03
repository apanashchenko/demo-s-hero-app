package com.s.hero.service;

import com.s.hero.model.Hero;
import com.s.hero.repository.HeroRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


/**
 * Created by alpa on 11/23/18
 */
@Service
@RequiredArgsConstructor
@Transactional
public class HeroService {

    private final HeroRepository heroRepository;

    public Optional<Hero> findById(Long id) {
        return heroRepository.findById(id);
    }

    public Iterable<Hero> findAll() {
        return heroRepository.findAll();
    }

    public Iterable<Hero> findAllActive() {
        return StreamSupport
                .stream(heroRepository.findAll().spliterator(), false)
                .filter(hero -> hero.getMessages().size() !=0 )
                .collect(Collectors.toList());
    }

    public List<Hero> findByStatus(String status) {
        return heroRepository.findByStatus(status);
    }

    public Hero add(Hero hero) {
        return heroRepository.save(hero);
    }



}
