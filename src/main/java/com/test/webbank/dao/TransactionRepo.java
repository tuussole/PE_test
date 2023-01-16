package com.test.webbank.dao;

import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;
import com.test.webbank.entity.Transaction;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    Transaction saveAndFlush(Transaction transaction);


    Transaction findById(long id);


    List<Transaction> findAll();

    List<Transaction> findByClient(Client client);

    List<Transaction> findBySender(Account sender);

    List<Transaction> findByRecipient(Account recipient);

    @Query(value = "SELECT * FROM TRANSACTIONS WHERE DATE(DATE) BETWEEN ?1 and ?2",nativeQuery = true)
    List<Transaction> findByDateOfTransaction(Date from, Date to);

}
