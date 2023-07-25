package com.recruitment.job.infrastructure.github.client;

import com.recruitment.job.infrastructure.github.client.dto.GithubUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;


@Component
@RequiredArgsConstructor
public class GithubHttpClient {
    private final String USERS_SUFFIX = "/users/{login}";
    private final WebClient githubWebClient;

    public GithubUserResponseDto getUserByLogin(String login){
        return githubWebClient.get()
                .uri(USERS_SUFFIX, login)
                .retrieve()
                .bodyToMono(GithubUserResponseDto.class)
                .block();
    }
}
