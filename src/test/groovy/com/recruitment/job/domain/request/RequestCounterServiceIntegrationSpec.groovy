package com.recruitment.job.domain.request

import com.recruitment.job.domain.request.service.RequestCounterService
import com.recruitment.job.infrastructure.postgres.counter.RequestCounterPersistenceServiceAdapter
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.context.annotation.Import
import spock.lang.Specification

@SpringBootTest
@Import(value = [RequestCounterService.class, RequestCounterPersistenceServiceAdapter.class])
class RequestCounterServiceIntegrationSpec extends Specification {

    @Autowired
    private RequestCounterService requestCounterService;

    @Autowired
    private RequestCounterPersistenceServiceAdapter requestCounterPersistenceServiceAdapter;

    def "should save new element and increment counter"(){
        given:
        def firstTimeOccurringLogin = "firstTimeOccurringLogin"

        when:
        requestCounterService.incrementRequestCounter(firstTimeOccurringLogin)

        then:
        def requestCounter = requestCounterPersistenceServiceAdapter.findByLogin(firstTimeOccurringLogin)
        requestCounter.isPresent()
        requestCounter.get().getLogin() == firstTimeOccurringLogin
        requestCounter.get().getRequestCount() == BigInteger.ONE
    }

    def "should increment existing login"(){
        given:
        def alreadyExistingLogin = "existing_login"
        def resource = new RequestCounter(alreadyExistingLogin)
        resource.setRequestCount(BigInteger.TEN)
        requestCounterPersistenceServiceAdapter.save(resource)

        when:
        requestCounterService.incrementRequestCounter(alreadyExistingLogin)

        then:
        def resourceAfter = requestCounterPersistenceServiceAdapter.findByLogin(alreadyExistingLogin)
        resourceAfter.isPresent()
        resourceAfter.get().getLogin() == alreadyExistingLogin
        resourceAfter.get().getRequestCount() == BigInteger.valueOf(11)
    }
}
