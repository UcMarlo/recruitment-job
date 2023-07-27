package com.recruitment.job.infrastructure.github.client.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class GithubClientConfiguration {

    @Bean
    public WebClient githubWebClient(@Value("${github.api.url}") String githubApiUrl) {
        return WebClient.create(githubApiUrl);
    }

}
