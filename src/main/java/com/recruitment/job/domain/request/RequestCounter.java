package com.recruitment.job.domain.request;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigInteger;

@Data
@AllArgsConstructor
public class RequestCounter {

    private String login;
    private BigInteger requestCount;

    private RequestCounter(String login) {
        this.login = login;
        this.requestCount = BigInteger.ONE;
    }

    public static RequestCounter newRequestCounter(String login){
        return new RequestCounter(login);
    }

    public void increaseCounter() {
        this.requestCount = this.requestCount.add(BigInteger.ONE);
    }
}
