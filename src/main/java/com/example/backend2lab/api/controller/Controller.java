package com.example.backend2lab.api.controller;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.api.model.AccountDTO;
import com.example.backend2lab.application.Services;
import com.example.backend2lab.domain.model.Account;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:46 <br>
 * Project: backend2Lab <br>
 */
@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/bank")
public class Controller {

    private final Services service;

    private static final Logger log =
            LoggerFactory.getLogger(Controller.class);

    @GetMapping("/")
    public Message root(){
        return new Message("Test",true);
    }

    @GetMapping("/login/{name}")
    public Message login(@PathVariable String name){
        if(name == null) {
            log.warn("Someone tried to login without a name.");
            return new Message("Name cant be null!",false);
        }
        AccountDTO dto = new AccountDTO(name);
        return service.login(dto);
    }

    @GetMapping("/openAccount/{name}")
    public Message openAccount(@PathVariable String name){
        if(name == null) {
            log.warn("Someone tried to open account without a name.");
            return new Message("Name cant be null!",false);
        }

        AccountDTO dto = new AccountDTO(name);

        return service.createNewAccount(dto);
    }

    @GetMapping("/deposit/{name}/{sum}")
    public Message deposit(@PathVariable String name, @PathVariable double sum){
        if(sum <= 0) {
            log.warn("Someone tried to deposit zero or less money.");
            return new Message("Sum cant be zero or below",false);
        }

        AccountDTO dto = new AccountDTO(name, sum);

        return service.deposit(dto);
    }

    @GetMapping("/withdraw/{name}/{sum}")
    public Message withdraw(@PathVariable String name, @PathVariable double sum){
        if(sum <= 0) {
            log.warn("Someone tried to withdraw more than they had in bank account.");
            return new Message("Sum cant be zero or below",false);
        }

        AccountDTO dto = new AccountDTO(name, sum);

        return service.withdraw(dto);
    }

}
