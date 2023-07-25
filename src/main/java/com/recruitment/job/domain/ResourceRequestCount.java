package com.recruitment.job.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@NoArgsConstructor
public class ResourceRequestCount {
    @Id
    private String login;
    private BigInteger requestCount;

    public ResourceRequestCount(String login) {
        this.login = login;
        requestCount = BigInteger.ZERO;
    }

    public void increaseCounter(){
        requestCount = requestCount.add(BigInteger.ONE);
    }

    public String getLogin() {
        return login;
    }

    public BigInteger getRequestCount() {
        return requestCount;
    }
}
