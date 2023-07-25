package com.recruitment.job.domain.request.port;

import com.recruitment.job.domain.request.ResourceRequestCount;

import java.util.Optional;

public interface ResourceRequestCountRepository {
    Optional<ResourceRequestCount> findByLogin(String login);
    void save(ResourceRequestCount resourceRequestCount);

}
