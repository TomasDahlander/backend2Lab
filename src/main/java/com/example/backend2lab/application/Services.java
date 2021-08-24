package com.example.backend2lab.application;

import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.domain.logic.Validation;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:06 <br>
 * Project: backend2Lab <br>
 */
public class Services {

    private Validation validation = new Validation();

    public Message login(String name){
        return validation.login(name);
    }

}
