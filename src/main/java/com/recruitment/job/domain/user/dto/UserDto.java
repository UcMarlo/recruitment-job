package com.recruitment.job.domain.user.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.ZonedDateTime;


@Value
public class UserDto {
    Long id;
    String login;
    String name;
    String type;
    String avatarUrl;
    ZonedDateTime createdAt;
    BigDecimal calculations;
}
