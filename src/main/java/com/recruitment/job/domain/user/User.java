package com.recruitment.job.domain.user;

import lombok.Value;

import java.time.ZonedDateTime;

@Value
public class User {
    Long id;
    String login;
    String name;
    String type;
    String avatarUri;
    ZonedDateTime createdAt;
    Long followers_count;
    Long publicRepositoriesCount;

}
