package com.test.webbank.model;

import com.test.webbank.entity.Account;
import com.test.webbank.entity.Transaction;
import com.test.webbank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class TransactionModel {

    @Autowired
    TransactionService transactionService;

    public boolean sufficientFunds(Account account, BigDecimal cash) {
        return account.getAssets().compareTo(cash) >= 0;
    }

    public void makeTransaction(Account sender, Account recipient, BigDecimal cash){
        if (sufficientFunds(sender,cash) && sender.getClient()!= null && recipient.getClient() != null) {
            Transaction trSender = new Transaction(sender.getClient(), Transaction.OperationType.CREDIT
                                                    ,cash, sender, recipient);
            Transaction trRecipient = new Transaction(recipient.getClient(), Transaction.OperationType.DEBIT
                    , cash, sender, recipient);
            sender.setAssets(sender.getAssets().subtract(cash));
            recipient.setAssets(recipient.getAssets().add(cash));
            transactionService.saveAndFlush(trSender);
            transactionService.saveAndFlush(trRecipient);
        }
    }

    public void fund(Account recipient, BigDecimal cash){
        if (recipient.getClient() != null) {
            Transaction trRecipient = new Transaction();
            trRecipient.setClient(recipient.getClient());
            trRecipient.setOpType(Transaction.OperationType.DEBIT);
            trRecipient.setSender(recipient);
            trRecipient.setRecipient(recipient);
            trRecipient.setCashAmount(cash);
            recipient.setAssets(recipient.getAssets().add(cash));
            transactionService.saveAndFlush(trRecipient);
        }
    }

    public void withdraw(Account sender, BigDecimal cash){
        if (sufficientFunds(sender,cash) && sender.getClient()!= null) {
            Transaction trSender = new Transaction();
            trSender.setClient(sender.getClient());
            trSender.setSender(sender);
            trSender.setRecipient(sender);
            trSender.setOpType(Transaction.OperationType.CREDIT);
            trSender.setCashAmount(cash);
            sender.setAssets(sender.getAssets().subtract(cash));
            transactionService.saveAndFlush(trSender);
        }
    }
}
