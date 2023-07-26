package com.recruitment.job.infrastructure.github;

import com.recruitment.job.domain.user.User;
import com.recruitment.job.domain.user.port.UserRepository;
import com.recruitment.job.infrastructure.github.client.GithubHttpClient;
import com.recruitment.job.infrastructure.github.exception.UserNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClientResponseException;

@Slf4j
@Repository
@RequiredArgsConstructor
public class GithubUserRepository implements UserRepository {

    private final GithubHttpClient githubHttpClient;

    @Override
    public User findUserByLogin(String login) {
        try {
            return githubHttpClient.getUserByLogin(login).toDomainObject();
        } catch (WebClientResponseException.NotFound e) {
            throw new UserNotFoundException("Cannot find user for login: " + login + " due to: " + e.getMessage());
        }
    }

}
