package com.recruitment.job.domain.user

import com.recruitment.job.domain.UserObjectMother
import com.recruitment.job.domain.user.port.UserRepository
import com.recruitment.job.domain.user.service.UserService
import spock.lang.Specification

import static org.spockframework.util.Assert.notNull

class UserServiceSpec extends Specification {

    def userRepository = Mock(UserRepository)
    def userCalculator = new UserCalculator()

    def userService = new UserService(userRepository, userCalculator)

    def 'should apply calculations to user'() {
        given:
        def userLogin = "knownUser"
        def userReturnedByRepository = UserObjectMother.getBuilder().build()
        1 * userRepository.findUserByLogin(userLogin) >> userReturnedByRepository

        when:
        def user = userService.findUserAndPerformCalculation(userLogin)

        then:
        with(user) {
            name == userReturnedByRepository.getName()
            id == userReturnedByRepository.getId()
            login == userReturnedByRepository.getLogin()
            name == userReturnedByRepository.getName()
            type == userReturnedByRepository.getType()
            avatarUri == userReturnedByRepository.getAvatarUri()
            createdAt == userReturnedByRepository.getCreatedAt()
            notNull(calculations)
        }
    }

    def 'should apply null for calculations when calculations are not possible'() {
        given:
        def userLogin = "knownUser"
        def userReturnedByRepository = UserObjectMother.getBuilder()
                .followersCount(0L)
                .build()
        1 * userRepository.findUserByLogin(userLogin) >> userReturnedByRepository

        when:
        def userAfterCalculations = userService.findUserAndPerformCalculation(userLogin)

        then:
        with(userAfterCalculations) {
            name == userReturnedByRepository.getName()
            id == userReturnedByRepository.getId()
            login == userReturnedByRepository.getLogin()
            name == userReturnedByRepository.getName()
            type == userReturnedByRepository.getType()
            avatarUri == userReturnedByRepository.getAvatarUri()
            createdAt == userReturnedByRepository.getCreatedAt()
            calculations == null
        }
    }
}
