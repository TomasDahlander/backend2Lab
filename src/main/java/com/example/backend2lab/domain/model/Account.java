package com.example.backend2lab.domain.model;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:36 <br>
 * Project: backend2Lab <br>
 */
public class Account {

    private Long id;

    private Person person;

    private double balance;

    public Account(Person person){
        this.person = person;
    }
}
