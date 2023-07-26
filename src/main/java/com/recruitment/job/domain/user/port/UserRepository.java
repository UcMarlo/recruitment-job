package com.recruitment.job.domain.user.port;

import com.recruitment.job.domain.user.User;

public interface UserRepository {
    User findUserByLogin(String login);
}
