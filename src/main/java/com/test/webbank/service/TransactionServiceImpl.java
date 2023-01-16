package com.test.webbank.service;

import com.test.webbank.dao.TransactionRepo;
import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;
import com.test.webbank.entity.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    TransactionRepo transactionRepo;

    @Override
    public Transaction saveAndFlush(Transaction transaction) {
        return transactionRepo.saveAndFlush(transaction);
    }

    @Override
    public List<Transaction> findAll() { return transactionRepo.findAll();}

    @Override
    public Transaction findById(long id) {
        return transactionRepo.findById(id);
    }

    @Override
    public List<Transaction> findByClient(Client client) {
        return transactionRepo.findByClient(client);
    }

    @Override
    public List<Transaction> findBySender(Account sender) {
        return transactionRepo.findBySender(sender);
    }

    @Override
    public List<Transaction> findByRecipient(Account recipient) {
        return transactionRepo.findByRecipient(recipient);
    }

    @Override
    public List<Transaction> findByDateOfTransaction(Date from, Date to) {
        return transactionRepo.findByDateOfTransaction(from, to);
    }
}
