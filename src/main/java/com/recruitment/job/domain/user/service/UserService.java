package com.recruitment.job.domain.user.service;

import com.recruitment.job.domain.user.User;
import com.recruitment.job.domain.user.UserCalculator;
import com.recruitment.job.domain.user.port.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserCalculator userCalculator;

    public User findUserAndPerformCalculation(String login) {
        User user = userRepository.findUserByLogin(login);

        user.applyCalculations(
                userCalculator.performCalculations(user).orElse(null)
        );
        return user;
    }

}
