package com.example.backend2lab.domain.model;

import lombok.*;

import javax.persistence.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:36 <br>
 * Project: backend2Lab <br>
 */
@Data
@RequiredArgsConstructor
@Entity
public class Account {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "balance")
    private double balance;

//    public Account(){}

//    public Account(String username){
//        this.username = username;
//    }
//
//    public Account(String username, double balance) {
//        this.username = username;
//        this.balance = balance;
//    }
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