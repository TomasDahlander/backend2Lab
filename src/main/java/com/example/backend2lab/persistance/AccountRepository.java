package com.example.backend2lab.persistance;

import com.example.backend2lab.domain.model.Account;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:24 <br>
 * Project: backend2Lab <br>
 */
@Repository
public interface AccountRepository extends CrudRepository<Account, Long> {

    Account findByUsername(String name);
}
