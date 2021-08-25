package com.example.backend2lab.domain.logic;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.model.Account;


/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:21 <br>
 * Project: backend2Lab <br>
 */
public class Validation {

    public Message login(Account account){
        if(account == null) return new Message("Person doesn't exists in database",false);
        else return new Message("Logged in", true, account);
    }

    public Message createAccount(Account account) {
        if(account == null) return new Message("OK",true,account);
        else return new Message("Already exists in database",false);
    }
}
