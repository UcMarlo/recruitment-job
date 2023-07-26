package com.recruitment.job.application.api


import com.recruitment.job.application.service.UserAccessCountingService
import com.recruitment.job.domain.UserObjectMother
import com.recruitment.job.infrastructure.github.exception.UserNotFoundException
import org.spockframework.spring.SpringBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.http.HttpHeaders
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import spock.lang.Specification

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*

@WebMvcTest(
        useDefaultFilters = false
)
@Import(value = [RestExceptionHandler.class, UsersRestController.class])
class UsersRestControllerMockMvcSpec extends Specification {

    @Autowired
    MockMvc mockMvc

    static final def ENDPOINT_PATH = '/api/v1/users/'

    @SpringBean
    UserAccessCountingService userAccessCountingService = Mock()

    def "should return 200 when a request is successfully handled by the flow"() {
        given:
        def login = "Billy"

        def user = UserObjectMother.getBuilder()
                .login(login)
                .calculations(BigDecimal.TEN)
                .build()

        and:
        1 * userAccessCountingService.accessUserResource(login) >> user

        when:
        def perform = mockMvc.perform(get(ENDPOINT_PATH + login))

        then:
        perform.andExpect(status().isOk())
                .andExpect(header()
                        .string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(content()
                        .json(OK_RESPONSE))
    }

    def "should return 404 when user resource cannot be found"() {
        given:
        def login = "Bush"

        def user = UserObjectMother.getBuilder()
                .login(login)
                .calculations(BigDecimal.TEN)
                .build()

        and:
        1 * userAccessCountingService.accessUserResource(login) >> { throw new UserNotFoundException("not found") }

        when:
        def perform = mockMvc.perform(get(ENDPOINT_PATH + login))

        then:
        perform.andExpect(status().isNotFound())
    }

    def "should return 500 when an unexpected exception occurred"() {
        given:
        def login = "Obama"

        def user = UserObjectMother.getBuilder()
                .login(login)
                .calculations(BigDecimal.TEN)
                .build()

        and:
        1 * userAccessCountingService.accessUserResource(login) >> { throw new NullPointerException("test") }

        when:
        def perform = mockMvc.perform(get(ENDPOINT_PATH + login))

        then:
        perform.andExpect(status().isInternalServerError())
    }

    static OK_RESPONSE = """
        {
          "id": 1234,
          "login": "Billy",
          "name": "Name Surname Nickname",
          "type": "User",
          "avatarUrl": "https://example.com/avatar",
          "createdAt": "2008-01-14T04:33:35Z",
          "calculations": 10
        }
    """

}
