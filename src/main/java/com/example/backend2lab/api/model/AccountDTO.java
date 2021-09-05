package com.example.backend2lab.api.model;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:40 <br>
 * Project: backend2Lab <br>
 */
public class AccountDTO {

    private String name;
    private String password;
    private double sum;

    public AccountDTO(String name) {
        this.name = name;
    }

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
