package com.example.backend2lab.application;

import com.example.backend2lab.api.model.AccountDTO;
import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.logic.AccountTransaction;
import com.example.backend2lab.domain.logic.Validation;
import com.example.backend2lab.domain.model.Account;
import com.example.backend2lab.persistance.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:06 <br>
 * Project: backend2Lab <br>
 */
public class Services {

    private Validation validation = new Validation();

    private AccountTransaction accountTransaction = new AccountTransaction();

    @Autowired
    private AccountRepository repository;

    @Transactional
    public Message login(AccountDTO dto){
        Account account = repository.findByName(dto.getName());
        return validation.login(account);
    }

    @Transactional
    public Message createNewAccount(AccountDTO dto) {
        Account account = repository.findByName(dto.getName());
        Message message = validation.createAccount(account);
        if(message.isStatus()) repository.save(account);
        return message;
    }

    @Transactional
    public Message deposit(AccountDTO dto) {
        Account account = repository.findByName(dto.getName());
        account = accountTransaction.deposit(account,dto.getSum());
        account = repository.save(account);
        return new Message("OK",true,account);
    }

    @Transactional
    public Message withdraw(AccountDTO dto) {
        Account account = repository.findByName(dto.getName());
        Message message = accountTransaction.withdraw(account,dto.getSum());
        if(message.isStatus()) repository.save(message.getAccount());
        return message;
    }
}
