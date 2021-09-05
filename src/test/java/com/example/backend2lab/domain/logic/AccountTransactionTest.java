package com.example.backend2lab.domain.logic;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-27 <br>
 * Time: 09:23 <br>
 * Project: backend2Lab <br>
 */
class AccountTransactionTest {

    AccountTransaction at = new AccountTransaction();

    @Test
    public void createAccountTest(){
        Account account = new Account(1L,"Kalle", "password",0);
        assertEquals(account.getBalance(), 0);
        assertNotEquals(account.getBalance(), 100);
    }

    @Test
    public void depositTest(){
        Account account = new Account(1L,"Kalle", "password",0);
        account = at.deposit(account,100);

        assertEquals(account.getBalance(),100);
    }

    @Test
    public void withdrawTest(){
        Account account = new Account(1L,"Kalle", "password",100);
        Message message = at.withdraw(account,50);

        assertEquals(message.getAccount().getBalance(),50);
        assertEquals(message.getMessage(),"OK");
        assertTrue(message.isStatus());
        assertNotEquals(message.getAccount().getBalance(),100);
    }

    @Test
    public void withdrawAboveExceededLimitTest(){
        Account account = new Account(1L,"Kalle", "password",100);

        Message message = at.withdraw(account,200);

        assertFalse(message.isStatus());
        assertEquals(message.getMessage(),"Not enough funding on account");
    }

}