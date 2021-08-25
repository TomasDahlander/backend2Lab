package com.example.backend2lab.api.model;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:40 <br>
 * Project: backend2Lab <br>
 */
public class AccountDTO {

    private String name;

    private double sum;

    public AccountDTO(String name) {
        this.name = name;
    }

    public AccountDTO(String name, double sum) {
        this.name = name;
        this.sum = sum;
    }

    public String getName() {
        return name;
    }

    public double getSum() {
        return sum;
    }
}
