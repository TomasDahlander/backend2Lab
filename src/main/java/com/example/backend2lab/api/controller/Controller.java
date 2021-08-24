package com.example.backend2lab.api.controller;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.api.model.AccountDTO;
import com.example.backend2lab.application.Services;
import org.springframework.web.bind.annotation.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 14:46 <br>
 * Project: backend2Lab <br>
 */
@RestController
public class Controller {

    private Services service = new Services();

    @GetMapping("/login")
    public Message login(@PathVariable String name){
        if(name == null) return new Message("Name cant be null!",false);

        // -->

        return new Message();
    }

    @GetMapping("/openAccount")
    public Message openAccount(@PathVariable String name){

        return new Message();
    }

    @GetMapping("/deposit")
    public Message deposit(@PathVariable String name, @PathVariable double sum, @PathVariable String type){
        AccountDTO t = new AccountDTO(name, sum, type);

        return new Message();
    }

    @GetMapping("/withdraw")
    public Message withdraw(){

        return new Message();
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
