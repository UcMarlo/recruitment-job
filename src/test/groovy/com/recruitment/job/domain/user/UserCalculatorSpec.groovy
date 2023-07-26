package com.recruitment.job.domain.user

import com.recruitment.job.domain.UserObjectMother
import spock.lang.Specification

class UserCalculatorSpec extends Specification {

    UserCalculator userCalculator = new UserCalculator()

    def "should return the correct calculation result"() {
        expect:
        def result = userCalculator.performCalculations(UserObjectMother.getBuilder()
                .followersCount(followersCount)
                .publicRepositoriesCount(publicRepositoriesCount)
                .build()).get()

        result == expectedResult

        where:
        followersCount | publicRepositoriesCount | expectedResult
        9900           | 8                       | BigDecimal.valueOf(0.0061)
        6              | 8                       | BigDecimal.valueOf(10)
        60             | 8                       | BigDecimal.valueOf(1)
        600            | 8                       | BigDecimal.valueOf(0.1)
    }

    def "should return optional empty for division by zero"() {
        when:
        def calculations = userCalculator.performCalculations((UserObjectMother.getBuilder()
                .followersCount(0L)
                .publicRepositoriesCount(100L)
                .build()))

        then:
        calculations.isEmpty()
    }
}
