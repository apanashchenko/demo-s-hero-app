package com.s.hero.repository;

import com.s.hero.model.Hero;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by alpa on 11/23/18
 */
@Repository
public interface HeroRepository extends CrudRepository<Hero, Long> {


    @Query(value = "select * from hero where status = :status", nativeQuery = true)
    List<Hero> findByStatus(String status);


}
