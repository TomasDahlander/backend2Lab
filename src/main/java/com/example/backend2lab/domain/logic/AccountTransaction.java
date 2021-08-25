package com.example.backend2lab.domain.logic;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.model.Account;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:21 <br>
 * Project: backend2Lab <br>
 */
public class AccountTransaction {
    public Account deposit(Account account, double sum) {
        double balance = account.getBalance() + sum;
        account.setBalance(balance);
        return account;
    }

    public Message withdraw(Account account, double sum) {
        double balance = account.getBalance() - sum;
        if(balance < 0) return new Message("Not enough funding on account",false);
        else{
            account.setBalance(balance);
            return new Message("OK",true,account);
        }
    }
}
