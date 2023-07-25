package com.recruitment.job.domain.repositories.ports;

import com.recruitment.job.domain.ResourceRequestCount;

import java.util.Optional;

public interface ResourceRequestCountRepository {
    Optional<ResourceRequestCount> findFirstByLogin(String login);
}
