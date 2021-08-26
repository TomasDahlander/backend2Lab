package com.example.backend2lab.demos;

import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;


/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-26 <br>
 * Time: 14:02 <br>
 * Project: backend2Lab <br>
 */
class DemoAccountTest {

    private final String object = "{\"balance\":0.0,\"holder\":\"Kalle\"}";
    private final DemoAccount demoAccount = new DemoAccount(0,"Kalle");

    @Test
    public void deserializebleTest() throws JsonProcessingException {
        DemoAccount da1 = new DemoAccount(0,"Kalle");
        Demo demo = new Demo();

        DemoAccount da2 = demo.deserializeble(object);

        assertEquals(da1.getClass(),da2.getClass());
        assertEquals(da1.getBalance(),da2.getBalance());
        assertEquals(da1.getHolder(),da2.getHolder());
    }

    @Test
    public void serializebleTest() throws JsonProcessingException {
        Demo demo = new Demo();
        String json = demo.serializeble(demoAccount);

        assertEquals(json,object);
    }
}