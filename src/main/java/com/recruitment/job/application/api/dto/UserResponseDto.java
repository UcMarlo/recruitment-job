package com.recruitment.job.application.api.dto;

import com.recruitment.job.domain.user.User;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record UserResponseDto(
        Long id,
        String login,
        String name,
        String type,
        String avatarUrl,
        ZonedDateTime createdAt,
        BigDecimal calculations
) {

    public static UserResponseDto fromUser(User user) {
        return new UserResponseDto(
                user.getId(),
                user.getLogin(),
                user.getName(),
                user.getType(),
                user.getAvatarUri(),
                user.getCreatedAt(),
                user.getCalculations()
        );
    }
}
