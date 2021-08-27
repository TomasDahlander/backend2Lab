package com.example.backend2lab.domain.logic;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.model.Account;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-27 <br>
 * Time: 09:39 <br>
 * Project: backend2Lab <br>
 */
class ValidationTest {

    Validation v = new Validation();

    @Test
    public void loginTest(){
        Account account = new Account(1L,"Kalle",0);
        Account accountNull = null;

        Message m1 = v.login(account);
        Message m2 = v.login(accountNull);

        assertEquals(m1.getMessage(),"Logged in");
        assertEquals(m2.getMessage(),"Person doesn't exists in database");
    }

    @Test
    public void createAccountTest(){
        Account account = new Account(1L,"Kalle",0);
        Account accountNull = null;

        Message m1 = v.createAccount(account);
        Message m2 = v.createAccount(accountNull);

        assertEquals(m1.getMessage(),"Already exists in database");
        assertFalse(m1.isStatus());

        assertEquals(m2.getMessage(),"OK");
        assertTrue(m2.isStatus());
    }
}
