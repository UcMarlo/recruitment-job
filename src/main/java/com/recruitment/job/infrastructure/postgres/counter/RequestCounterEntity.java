package com.recruitment.job.infrastructure.postgres.counter;

import com.recruitment.job.domain.request.RequestCounter;
import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@NoArgsConstructor
@Table(name = "request_counter")
public class RequestCounterEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String login;
    private BigInteger requestCount;

    public RequestCounterEntity(String login, BigInteger requestCount) {
        this.login = login;
        this.requestCount = requestCount;
    }

    public String getLogin() {
        return login;
    }

    public BigInteger getRequestCount() {
        return requestCount;
    }

    public void updateEntity(RequestCounter requestCounter){
        this.requestCount = requestCounter.getRequestCount();
    }

    public RequestCounter toDomainObject(){
        return new RequestCounter(this.login, this.requestCount);
    }
}
