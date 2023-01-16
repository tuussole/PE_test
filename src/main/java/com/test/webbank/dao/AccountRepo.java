package com.test.webbank.dao;

import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountRepo extends JpaRepository<Account, Long> {

    Account saveAndFlush(Account account);

    List<Account> findByClient(Client client);

    Account findById(long id);

    Account findByAccountNumber(long accountNumber);
}
