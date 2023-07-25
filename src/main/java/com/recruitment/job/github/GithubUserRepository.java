package com.recruitment.job.github;

import com.recruitment.job.domain.User;
import com.recruitment.job.domain.repositories.ports.UserRepository;
import com.recruitment.job.github.dto.GithubUserResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class GithubUserRepository implements UserRepository {
    private final GithubHttpClient githubHttpClient;
    @Override
    public Optional<User> findUserByLogin(String login) {
        GithubUserResponseDto githubUser = githubHttpClient.getUserByLogin(login);

        return Optional.of(
                new User(
                        githubUser.getId(),
                        githubUser.getLogin(),
                        githubUser.getName(),
                        githubUser.getType(),
                        githubUser.getAvatar_url(),
                        githubUser.getCreated_at(),
                        githubUser.getFollowers(),
                        githubUser.getPublic_repos()
                )
        );
    }
}
