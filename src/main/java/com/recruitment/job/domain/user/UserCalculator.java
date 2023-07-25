package com.recruitment.job.domain.user;

import lombok.extern.log4j.Log4j2;
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
            return Optional.of(CALCULATION_NUMERATOR.divide(calculateDenominator(followers_count, publicRepositoriesCount), 3, RoundingMode.HALF_UP));
        } catch (ArithmeticException exception){
            return Optional.empty();
        }
    }
    private BigDecimal calculateDenominator(Long followers_count, Long publicRepositoriesCount) {
        return BigDecimal.valueOf(followers_count)
                .multiply(BigDecimal.valueOf(publicRepositoriesCount)
                        .add(PUBLIC_REPOSITORIES_COUNT_ADDITION)
                );
    }
}
