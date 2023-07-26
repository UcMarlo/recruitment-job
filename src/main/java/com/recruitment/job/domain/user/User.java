package com.recruitment.job.domain.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

@Data
@Builder
@AllArgsConstructor
public class User {

    private Long id;
    private String login;
    private String name;
    private String type;
    private String avatarUri;
    private ZonedDateTime createdAt;
    private Long followersCount;
    private Long publicRepositoriesCount;
    private BigDecimal calculations;

    public void applyCalculations(BigDecimal calculations) {
        this.calculations = calculations;
    }

}
