package com.example.backend2lab.domain.logic;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.model.Account;
import com.example.backend2lab.persistance.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:21 <br>
 * Project: backend2Lab <br>
 */
public class Validation {

    @Autowired
    AccountRepository repository;

    public Message login(String name){
        Account account = repository.findByName(name);
        if(account == null) return new Message("Person doesn't exists in database",false);
        else return new Message("Logged in", true, account);
    }

}
