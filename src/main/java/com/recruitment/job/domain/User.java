package com.recruitment.job.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@Getter
public class User {
    int id;
    String login;
    String name;
    String type;
    String avatarUri;
    ZonedDateTime createdAt;
    Long followers_count;
    Long publicRepositoriesCount;

//    private final UserCalculator userCalculator;
//
//    public BigDecimal getCalculations(){
//        return userCalculator.performCalculations(this.followers_count, this.publicRepositoriesCount).orElse(null);
//    }
}
