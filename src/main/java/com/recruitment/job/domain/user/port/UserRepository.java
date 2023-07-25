package com.recruitment.job.domain.user.port;

import com.recruitment.job.domain.user.User;

import java.util.Optional;

public interface UserRepository {
    Optional<User> findUserByLogin(String login);
}
