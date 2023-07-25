package com.recruitment.job.api.dto;

import lombok.Value;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Value
public class UserMetadataDto {
    Long id;
    String login;
    String name;
    String type;
    String avatarUrl;
    ZonedDateTime createdAt;
    BigDecimal calculations;
}
