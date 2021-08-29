package com.example.backend2lab.api.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-29 <br>
 * Time: 16:44 <br>
 * Project: backend2Lab <br>
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class RiskAssessment {

    private boolean pass;

    @JsonCreator
    public RiskAssessment(@JsonProperty("pass") boolean pass) {
        this.pass = pass;
    }

    public boolean isPass() {
        return pass;
    }

    public void setPass(boolean pass) {
        this.pass = pass;
    }
}
