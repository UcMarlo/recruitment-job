package com.recruitment.job.infrastructure.postgres.counter;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRequestCountJakartaRepository extends CrudRepository<RequestCounterEntity, Long> {
    Optional<RequestCounterEntity> findByLogin(String login);
}
