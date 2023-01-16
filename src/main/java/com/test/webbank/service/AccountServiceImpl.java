package com.test.webbank.service;

import com.test.webbank.dao.AccountRepo;
import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepo accountRepo;

    @Override
    public Account saveAndFlush(Account account) {
        return accountRepo.saveAndFlush(account);
    }

    @Override
    public List<Account> findByClient(Client client) {
        return accountRepo.findByClient(client);
    }

    @Override
    public Account findById(long id) {
        return accountRepo.findById(id);
    }

    @Override
    public Account findByAccountNumber(long accountNumber) {
        return accountRepo.findByAccountNumber(accountNumber);
    }
}
