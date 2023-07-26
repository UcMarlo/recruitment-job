package com.recruitment.job.infrastructure.github;

import com.recruitment.job.domain.user.User;
import com.recruitment.job.domain.user.port.UserRepository;
import com.recruitment.job.infrastructure.github.client.GithubHttpClient;
import com.recruitment.job.infrastructure.github.client.dto.GithubUserResponseDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
@Slf4j
public class GithubUserRepository implements UserRepository {
    private final GithubHttpClient githubHttpClient;
    @Override
    public Optional<User> findUserByLogin(String login) {
        try {
            return Optional.of(
                    convertToUser(
                            githubHttpClient.getUserByLogin(login)));
        } catch (WebClientResponseException.NotFound webClientNotFoundException){
            log.debug("Github returned 404 - NOT FOUND: ", webClientNotFoundException);
            return Optional.empty();
        }
    }

    private static User convertToUser(GithubUserResponseDto githubUser) {
        return new User(
                githubUser.getId(),
                githubUser.getLogin(),
                githubUser.getName(),
                githubUser.getType(),
                githubUser.getAvatar_url(),
                githubUser.getCreated_at(),
                githubUser.getFollowers(),
                githubUser.getPublic_repos()
        );
    }
}
