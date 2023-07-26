package job.domain.user

import com.recruitment.job.domain.user.UserCalculator
import spock.lang.Specification

class UserCalculatorTest extends Specification {

    UserCalculator userCalculator = new UserCalculator()

    def "should return the correct calculation result"() {
        expect:
        userCalculator.performCalculations(a, b).get() == expectedResult

        where:
        a       | b     | expectedResult
        9900    | 8     | BigDecimal.valueOf(0.0061)
        6       | 8     | BigDecimal.valueOf(10)
        60      | 8     | BigDecimal.valueOf(1)
        600     | 8     | BigDecimal.valueOf(0.1)
    }

    def "should return optional empty for division by zero"() {
        expect:
        userCalculator.performCalculations(0, 100) == Optional.empty()
    }
}
