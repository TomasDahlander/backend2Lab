package com.example.backend2lab.api.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureDataJpa;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.TestPropertySourceUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-27 <br>
 * Time: 11:01 <br>
 * Project: backend2Lab <br>
 */
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ExtendWith(SpringExtension.class)
@Testcontainers
@ContextConfiguration(initializers = ControllerTest.Lab1ApplicationTestsContextInitializer.class)
@AutoConfigureMockMvc
@AutoConfigureDataJpa
class ControllerTest {

    @Container
    private static final MySQLContainer db = new MySQLContainer("mysql:8.0.26").withPassword("password");

    @Autowired
    MockMvc mockMvc;

    @Test
    public void loginTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/login/Karl"))
                .andExpect(status().is2xxSuccessful());
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/log/Karl"))
                .andExpect(status().is4xxClientError());
    }

    @Test
    public void openAccountTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/openAccount/Karl"))
                .andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/openAcc/Karl"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void depositTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/deposit/Karl/100"))
                .andExpect(status().is(200));
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/depo/Karl/100"))
                .andExpect(status().is(404));
    }

    @Test
    public void withdrawTest() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/withdraw/Karl/100"))
                .andExpect(status().is(200));
        mockMvc.perform(MockMvcRequestBuilders.get("/bank/with/Karl/100"))
                .andExpect(status().is(404));
    }

    public static class Lab1ApplicationTestsContextInitializer
            implements ApplicationContextInitializer<ConfigurableApplicationContext> {

        @Override
        public void initialize(ConfigurableApplicationContext configurableApplicationContext) {
            String host = db.getJdbcUrl();
            TestPropertySourceUtils.addInlinedPropertiesToEnvironment(
                    configurableApplicationContext,
                    "spring.datasource.url=" + host, "flyway.url=" + host);

        }
    }
}
