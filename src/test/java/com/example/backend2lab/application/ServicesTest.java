package com.example.backend2lab.application;

import com.example.backend2lab.api.model.AccountDTO;
import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.model.Account;
import com.example.backend2lab.persistance.AccountRepository;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-27 <br>
 * Time: 10:22 <br>
 * Project: backend2Lab <br>
 */
class ServicesTest {

    AccountRepository accountRepository = mock(AccountRepository.class);
    Services service = new Services(accountRepository);

    @Test
    void loginTest() {
        when(accountRepository.findByUsername("Kalle")).thenReturn(new Account(1L,"Kalle",100));
        when(accountRepository.findByUsername("Andy")).thenReturn(null);

        Message m1 = service.login(new AccountDTO("Kalle",0));
        Message m2 = service.login(new AccountDTO("Andy",0));

        assertEquals(m1.getAccount().getBalance(),100);
        assertEquals(m1.getMessage(),"Logged in");
        assertTrue(m1.isStatus());

        assertEquals(m2.getMessage(),"Person doesn't exists in database");
        assertFalse(m2.isStatus());
        assertNull(m2.getAccount());

        verify(accountRepository,times(2)).findByUsername(anyString());
    }

    @Test
    void createNewAccountTest() {
        when(accountRepository.findByUsername("Kalle")).thenReturn(new Account(1L,"Kalle",100));
        when(accountRepository.findByUsername("Andy")).thenReturn(null);

        Message message = service.createNewAccount(new AccountDTO("Kalle",0));
        Message messageNull = service.createNewAccount(new AccountDTO("Andy",0));

        assertEquals(message.getMessage(),"Already exists in database");
        assertFalse(message.isStatus());

        assertEquals(messageNull.getAccount().getUsername(),"Andy");
        assertTrue(messageNull.isStatus());

        verify(accountRepository,times(2)).findByUsername(anyString());
        verify(accountRepository).save(any());
    }

    @Test
    void depositTest() {
        Account account = new Account(1L,"Kalle",0);
        when(accountRepository.findByUsername("Kalle")).thenReturn(account);
        when(accountRepository.save(new Account(1L,"Kalle",100))).thenReturn(new Account(1L,"Kalle",100));
        Message message = service.deposit(new AccountDTO("Kalle",100));

        assertEquals(message.getMessage(),"OK");
        assertTrue(message.isStatus());
        assertEquals(message.getAccount().getBalance(),100);
        verify(accountRepository).save(any());
        verify(accountRepository).findByUsername("Kalle");
    }

    @Test
    void withdrawTest() {
        when(accountRepository.findByUsername("Kalle")).thenReturn(new Account(1L,"Kalle",100));
        Message m1 = service.withdraw(new AccountDTO("Kalle",100));

        when(accountRepository.findByUsername("Andy")).thenReturn(new Account(2L,"Andy",100));
        Message m2 = service.withdraw(new AccountDTO("Andy",200));

        assertEquals(m1.getAccount().getBalance(),0);
        assertTrue(m1.isStatus());

        assertEquals(m2.getMessage(),"Not enough funding on account");
        assertFalse(m2.isStatus());

        verify(accountRepository).save(any());
        verify(accountRepository,times(2)).findByUsername(anyString());
    }

    @Test
    void checkIfCreditIsOkTest(){
        String karl = "Karl";
        String kalle = "Kalle";

        assertEquals(karl.hashCode()%2,0);
        assertEquals(kalle.hashCode()%2,1);

        assertTrue(service.checkIfCreditIsOk("Karl"));
        assertFalse(service.checkIfCreditIsOk("Kalle"));
    }
}