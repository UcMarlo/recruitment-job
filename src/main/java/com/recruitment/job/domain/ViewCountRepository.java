package com.recruitment.job.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ViewCountRepository extends CrudRepository<String, ViewCount> {

    Optional<ViewCount> findFirstByLogin(String login);
}
