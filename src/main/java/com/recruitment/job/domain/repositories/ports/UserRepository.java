package com.recruitment.job.domain.repositories.ports;

import com.recruitment.job.domain.UserDto;

import java.util.Optional;

public interface UserRepository {
    Optional<UserDto> findUserByLogin(String login);
}
