package com.s.hero.repository;

import com.s.hero.model.RequestMessage;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by alpa on 11/23/18
 */
@Repository
public interface RequestMessageRepository extends CrudRepository<RequestMessage, Long> {

    @Query(value = "select * from request_message where hero_id = :heroId and status = :status", nativeQuery = true)
    Optional<RequestMessage> findByHeroIdAndStatus(Long heroId, String status);
}
