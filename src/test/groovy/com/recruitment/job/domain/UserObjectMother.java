package com.recruitment.job.domain;

import com.recruitment.job.domain.user.User;

import java.time.ZonedDateTime;

public class UserObjectMother {

    public static User.UserBuilder getBuilder() {
        return User.builder()
                .id(1234L)
                .login("login")
                .name("Name Surname Nickname")
                .type("User")
                .avatarUri("https://example.com/avatar")
                .createdAt(ZonedDateTime.parse("2008-01-14T04:33:35Z"))
                .followersCount(1L)
                .publicRepositoriesCount(1L);
    }

}

