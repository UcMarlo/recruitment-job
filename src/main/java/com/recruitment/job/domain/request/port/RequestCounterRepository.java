package com.recruitment.job.domain.request.port;

import com.recruitment.job.domain.request.RequestCounter;

import java.util.Optional;

public interface RequestCounterRepository {

    Optional<RequestCounter> findByLogin(String login);

    void save(RequestCounter requestCounter);

    void update(RequestCounter requestCounter);

}
