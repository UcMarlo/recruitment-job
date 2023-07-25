package com.recruitment.job.domain;

import java.math.BigDecimal;
import java.util.Optional;

public class UserCalculator {
    private final BigDecimal CALCULATION_NUMERATOR = BigDecimal.valueOf(6);
    private final BigDecimal PUBLIC_REPOSITORIES_COUNT_ADDITION = BigDecimal.valueOf(2);

    public Optional<BigDecimal> performCalculations(Long followers_count, Long publicRepositoriesCount){
        try {
            return Optional.of(CALCULATION_NUMERATOR.divide(calculateDenominator(followers_count, publicRepositoriesCount)));
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
