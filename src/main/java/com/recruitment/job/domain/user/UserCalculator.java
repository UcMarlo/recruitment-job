package com.recruitment.job.domain.user;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Slf4j
@Component
public class UserCalculator {

    private static final BigDecimal CALCULATION_NUMERATOR = BigDecimal.valueOf(6);
    private static final BigDecimal PUBLIC_REPOSITORIES_COUNT_ADDITION = BigDecimal.valueOf(2);

    public Optional<BigDecimal> performCalculations(User user) {
        try {
            BigDecimal followersCount = BigDecimal.valueOf(user.getFollowersCount());
            BigDecimal publicRepositoriesCount = BigDecimal.valueOf(user.getPublicRepositoriesCount());

            return Optional.of(CALCULATION_NUMERATOR.divide(followersCount, 5, RoundingMode.HALF_UP)
                    .multiply(publicRepositoriesCount.add(PUBLIC_REPOSITORIES_COUNT_ADDITION)));
        } catch (ArithmeticException e) {
            log.warn("An arithmetic exception occurred: {}", e.getMessage());
            return Optional.empty();
        }
    }
}
