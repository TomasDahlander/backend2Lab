package com.example.backend2lab.domain.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:36 <br>
 * Project: backend2Lab <br>
 */
@Setter
@Getter
@Entity
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "balance")
    private double balance;

    public Account(){}

    public Account(String username){
        this.username = username;
    }

    public Account(String username, double balance) {
        this.username = username;
        this.balance = balance;
    }


}

//    @Id
//    @Column(name = "id")
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private long id;
//
//    @Column(name = "balance")
//    private long balance;
//
//    @Column(name = "holder")
//    private String holder;
//
//    public Account() {
//        //For hibernate
//    }