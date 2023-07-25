package com.recruitment.job.domain.repositories.ports;

import com.recruitment.job.domain.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByLogin(String login);
}
