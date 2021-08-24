package com.example.backend2lab.persistance;

import com.example.backend2lab.domain.model.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by Tomas Dahlander <br>
 * Date: 2021-08-24 <br>
 * Time: 15:24 <br>
 * Project: backend2Lab <br>
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    Account findByName(String name);
}
