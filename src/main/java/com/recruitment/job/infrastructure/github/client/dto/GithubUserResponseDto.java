package com.recruitment.job.infrastructure.github.client.dto;

import com.recruitment.job.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.ZonedDateTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GithubUserResponseDto {
    private String login;
    private Long id;
    private String node_id;
    private String avatar_url;
    private String gravatar_id;
    private String url;
    private String html_url;
    private String followers_url;
    private String following_url;
    private String gists_url;
    private String starred_url;
    private String subscriptions_url;
    private String organizations_url;
    private String repos_url;
    private String events_url;
    private String received_events_url;
    private String type;
    private boolean site_admin;
    private String name;
    private String company;
    private String blog;
    private String location;
    private String email;
    private boolean hireable;
    private String bio;
    private String twitter_username;
    private Long public_repos;
    private int public_gists;
    private Long followers;
    private Long following;
    private ZonedDateTime created_at;
    private ZonedDateTime updated_at;
    private int private_gists;
    private int total_private_repos;
    private int owned_private_repos;
    private int disk_usage;
    private int collaborators;
    private boolean two_factor_authentication;
    private PlanDto plan;

    public User toDomainObject() {
        return new User(
                this.id,
                this.login,
                this.name,
                this.type,
                this.avatar_url,
                this.created_at,
                this.followers,
                this.public_repos,
                null
        );
    }

}