package com.recruitment.job.domain.request;

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
        this.requestCount = this.requestCount.add(BigInteger.ONE);
    }

    public String getLogin() {
        return login;
    }

    public BigInteger getRequestCount() {
        return requestCount;
    }
}
