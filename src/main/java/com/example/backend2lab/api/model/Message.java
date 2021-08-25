package com.example.backend2lab.api.model;

import com.example.backend2lab.domain.model.Account;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:51 <br>
 * Project: backend2Lab <br>
 */
public class Message {

    private String message;

    private boolean status;

    private Account account;

    public Message(){}

    public Message(String message, boolean status){
        this.message = message;
        this.status = status;
    }

    public Message(String message, boolean status, Account account) {
        this.message = message;
        this.status = status;
        this.account = account;
    }

    public String getMessage() {
        return message;
    }

    public boolean isStatus() {
        return status;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account){
        this.account = account;
    }
}
