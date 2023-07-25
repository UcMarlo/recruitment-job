package com.recruitment.job.api.dto;

import com.recruitment.job.domain.user.dto.UserDto;
import lombok.Value;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

public record UserResponseDto(Long id, String login, String name, String type, String avatarUrl,
                              ZonedDateTime createdAt, BigDecimal calculations) {
    public static UserResponseDto createResponseDto(UserDto userDto) {
        return new UserResponseDto(
                userDto.getId(),
                userDto.getLogin(),
                userDto.getName(),
                userDto.getType(),
                userDto.getAvatarUrl(),
                userDto.getCreatedAt(),
                userDto.getCalculations()
        );
    }
}
