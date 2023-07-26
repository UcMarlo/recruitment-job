package com.recruitment.job.application.service;

import com.recruitment.job.domain.request.service.RequestCounterService;
import com.recruitment.job.domain.user.User;
import com.recruitment.job.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserAccessCountingService {
    private final UserService userService;
    private final RequestCounterService requestCounterService;

    public User accessUserResource(String login) {
        User user = userService.findUserAndPerformCalculation(login);
        requestCounterService.incrementRequestCounter(login);

        return user;
    }
}
