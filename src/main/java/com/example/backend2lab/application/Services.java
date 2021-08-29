package com.example.backend2lab.application;

import com.example.backend2lab.api.model.AccountDTO;
import com.example.backend2lab.api.model.Message;
import com.example.backend2lab.api.model.RiskAssessment;
import com.example.backend2lab.domain.logic.AccountTransaction;
import com.example.backend2lab.domain.logic.Validation;
import com.example.backend2lab.domain.model.Account;
import com.example.backend2lab.persistance.AccountRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Objects;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:06 <br>
 * Project: backend2Lab <br>
 */
@Service
public class Services {

    private final Validation validation = new Validation();

    private final AccountTransaction accountTransaction = new AccountTransaction();

    private final AccountRepository repository;

    public Services(AccountRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public Message login(AccountDTO dto){
        Account account = repository.findByUsername(dto.getName());
        return validation.login(account);
    }

    @Transactional
    public Message createNewAccount(AccountDTO dto) {
        Account account = repository.findByUsername(dto.getName());

        Message message = validation.createAccount(account);

        if(message.isStatus()){
            account = new Account();
            account.setUsername(dto.getName());
            account.setBalance(0);
            message.setAccount(account);
            repository.save(account);
        }
        return message;
    }

    @Transactional
    public Message deposit(AccountDTO dto) {
        Account account = repository.findByUsername(dto.getName());
        account = accountTransaction.deposit(account,dto.getSum());
        account = repository.save(account);
        return new Message("OK",true,account);
    }

    @Transactional
    public Message withdraw(AccountDTO dto) {
        Account account = repository.findByUsername(dto.getName());
        Message message = accountTransaction.withdraw(account,dto.getSum());
        if(message.isStatus()) repository.save(message.getAccount());
        return message;
    }

    public boolean checkIfCreditIsOk(String name){
        RestTemplate restTemplate = new RestTemplate();
//        ObjectMapper objectMapper = new ObjectMapper();
        String url = "http://localhost:8080/risk/"+name;

        return restTemplate.getForEntity(url,RiskAssessment.class)
                .getBody().isPass();

    }

//    public List<Anforande> fetchAnforanden() {
//        return restTemplate.getForEntity(baseUrl + path, AnforandeResponseDto.class)
//                .getBody().getAnforandeLista().getAnforande()
//                .stream()
//                .map(dto -> new Anforande(dto.getDokId()))
//                .collect(Collectors.toList());
//    }

    public Iterable<Account> findAllTest() {
        return repository.findAll();
    }
}
