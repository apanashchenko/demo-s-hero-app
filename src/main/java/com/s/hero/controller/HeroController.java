package com.s.hero.controller;

import com.s.hero.model.Hero;
import com.s.hero.service.HeroService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * Created by alpa on 11/23/18
 */
@RestController
@RequiredArgsConstructor
public class HeroController {

    private final HeroService heroService;

    @RequestMapping(value = "/heroes", method = RequestMethod.GET)
    public ResponseEntity getAllHeroes() {
        return new ResponseEntity<>(heroService.findAllActive(), HttpStatus.OK);
    }

    @RequestMapping(value = "/hero", method = RequestMethod.POST)
    public ResponseEntity createHero(@RequestBody Hero hero) {
        return new ResponseEntity<>(heroService.add(hero), HttpStatus.OK);
    }

    @RequestMapping(value = "/heroresponse/{id}", method = RequestMethod.POST)
    public ResponseEntity createHeroResponse(@PathVariable("id") Long id, @RequestBody Hero updateHero) {
        Optional<Hero> hero = heroService.findById(id);
        if (hero.isPresent()) {
            Hero upHero = hero.get();
            upHero.setStatus(updateHero.getStatus());
            upHero.setArproverComment(updateHero.getArproverComment());
            return new ResponseEntity<>(heroService.add(upHero), HttpStatus.OK);
        }
        return  new ResponseEntity<>("Hero with id " + id + " not found", HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/hero/{id}", method = RequestMethod.GET)
    public ResponseEntity getHeroById(@PathVariable("id") Long id) {
        Optional<Hero> hero = heroService.findById(id);
        return hero.<ResponseEntity>map(hero1 -> new ResponseEntity<>(hero1, HttpStatus.OK)).orElseGet(() ->
                new ResponseEntity<>("Hero with id " + id + " not found", HttpStatus.NOT_FOUND));

    }

}
