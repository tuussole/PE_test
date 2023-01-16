package com.test.webbank.service;

import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;

import java.util.List;

public interface AccountService {

    Account saveAndFlush(Account account);

    List<Account> findByClient(Client client);

    Account findById(long id);

    Account findByAccountNumber(long accountNumber);
}
