package com.example.backend2lab.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:40 <br>
 * Project: backend2Lab <br>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class AccountDTO {

    @JsonProperty
    private String name;
    @JsonProperty
    private String password;
    @JsonProperty
    private double sum;

    public AccountDTO(String name) {
        this.name = name;
    }

    @JsonCreator
    public AccountDTO(String name, String password) {
        this.name = name;
        this.password = password;
    }

    public AccountDTO(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public double getSum() {
        return sum;
    }
}
