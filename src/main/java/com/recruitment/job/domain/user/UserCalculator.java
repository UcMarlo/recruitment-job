package com.recruitment.job.domain.user;

import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Optional;

@Component
public class UserCalculator {
    private final BigDecimal CALCULATION_NUMERATOR = BigDecimal.valueOf(6);
    private final BigDecimal PUBLIC_REPOSITORIES_COUNT_ADDITION = BigDecimal.valueOf(2);

    public Optional<BigDecimal> performCalculations(Long followers_count, Long publicRepositoriesCount){
        try {
            return Optional.of(
                    CALCULATION_NUMERATOR.divide(BigDecimal.valueOf(followers_count), 5, RoundingMode.HALF_UP)
                            .multiply(BigDecimal.valueOf(publicRepositoriesCount).add(PUBLIC_REPOSITORIES_COUNT_ADDITION))
            );
        } catch (ArithmeticException exception){
            return Optional.empty();
        }
    }
}
