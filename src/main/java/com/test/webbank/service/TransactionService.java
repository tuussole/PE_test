package com.test.webbank.service;

import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;
import com.test.webbank.entity.Transaction;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface TransactionService {

    Transaction saveAndFlush(Transaction transaction);

    List<Transaction> findAll();

    Transaction findById(long id);

    List<Transaction> findByClient(Client client);

    List<Transaction> findBySender(Account sender);

    List<Transaction> findByRecipient(Account recipient);

    List<Transaction> findByDateOfTransaction(Date from, Date to);

}
