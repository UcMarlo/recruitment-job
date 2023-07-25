package com.recruitment.job.infrastructure.counter;

import com.recruitment.job.domain.request.ResourceRequestCount;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResourceRequestCountJakartaRepository extends CrudRepository<ResourceRequestCount, String> {
    Optional<ResourceRequestCount> findByLogin(String login);
}
