package com.example.backend2lab.api.model;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:40 <br>
 * Project: backend2Lab <br>
 */
public class AccountDTO {

    public AccountDTO(String name, double sum, String type) {
        this.name = name;
        this.sum = sum;
        this.type = type;
    }

    private String name;

    private double sum;

    private String type;

}
