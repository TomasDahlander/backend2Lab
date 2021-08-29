package com.example.backend2lab.Demo;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-29 <br>
 * Time: 16:12 <br>
 * Project: backend2Lab <br>
 */
public class DemoAccount {

    private final double balance;
    private final String holder;

    public double getBalance() {
        return balance;
    }

    public String getHolder() {
        return holder;
    }

    @JsonCreator
    public DemoAccount(@JsonProperty("balance") double balance, @JsonProperty("holder") String holder) {
        this.balance = balance;
        this.holder = holder;
    }
}

class Demo{

    public DemoAccount deserializeble(String json) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        DemoAccount da = om.readValue(json,DemoAccount.class);
        return da;
    }

    public String serializeble(DemoAccount demoAccount) throws JsonProcessingException {
        ObjectMapper om = new ObjectMapper();
        String json = om.writeValueAsString(demoAccount);
        return json;
    }

}