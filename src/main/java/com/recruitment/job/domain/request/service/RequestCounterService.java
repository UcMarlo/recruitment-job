package com.recruitment.job.domain.request.service;

import com.recruitment.job.domain.request.RequestCounter;
import com.recruitment.job.domain.request.port.RequestCounterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RequestCounterService {
    private final RequestCounterRepository requestCounterRepository;

    public void incrementRequestCounter(String login) {
        requestCounterRepository.findByLogin(login).ifPresentOrElse(
                this::updateRequestCounter,
                () -> initializeRequestCounter(login)
        );
    }

    private void updateRequestCounter(RequestCounter requestCounter) {
        requestCounter.increaseCounter();
        requestCounterRepository.update(requestCounter);
    }

    private void initializeRequestCounter(String login) {
        RequestCounter newRequestCounter = RequestCounter.newRequestCounter(login);
        requestCounterRepository.save(newRequestCounter);
    }
}
