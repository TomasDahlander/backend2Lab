package com.example.backend2lab.api.controller;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.api.model.AccountDTO;
import com.example.backend2lab.application.Services;
import com.example.backend2lab.domain.model.Account;
import lombok.RequiredArgsConstructor;
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

//    @GetMapping("/delete/{id}")

    @GetMapping("/")
    public Message root(){
        return new Message("Test",true);
    }

    @GetMapping("/test")
    public String test(){
        Iterable<Account> list = service.findAllTest();
        return list.toString();
    }

    @GetMapping("/login/{name}")
    public Message login(@PathVariable String name){
        if(name == null) {
            System.out.println("namn var null");
            return new Message("Name cant be null!",false);
        }
        AccountDTO dto = new AccountDTO(name);
        return service.login(dto);
    }

    @GetMapping("/openAccount/{name}")
    public Message openAccount(@PathVariable String name){
        if(name == null) return new Message("Name cant be null!",false);

        AccountDTO dto = new AccountDTO(name);

        return service.createNewAccount(dto);
    }

    @GetMapping("/deposit/{name}/{sum}")
    public Message deposit(@PathVariable String name, @PathVariable double sum){
        if(sum <= 0) return new Message("Sum cant be zero or below",false);

        AccountDTO dto = new AccountDTO(name, sum);

        return service.deposit(dto);
    }

    @GetMapping("/withdraw/{name}/{sum}")
    public Message withdraw(@PathVariable String name, @PathVariable double sum){
        if(sum <= 0) return new Message("Sum cant be zero or below",false);

        AccountDTO dto = new AccountDTO(name, sum);

        return service.withdraw(dto);
    }

//    @GetMapping("/bank/{userId}/account")
//    public String openAccount(@PathVariable("userId") String userId) {
//        return "redirect:/bank/unknown/account/1";
//    }
//
//    @GetMapping("/bank/{userId}/account/{accountId}")
//    public String bankForm(@PathVariable("userId") String userId, @PathVariable("accountId") Long accountId, Model model) {
//        model.addAttribute("userId", userId);
//        model.addAttribute("accountId", accountId);
//        return "bank";
//    }

}
