package com.test.webbank.model;

import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;
import com.test.webbank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ClientModel {

    @Autowired
    AccountService accountService;

    public boolean clientIsCorrect(Client client) {
        return (client.getFirstName() != null && client.getLastName() != null &&
                client.getFirstName().length() > 0 && client.getLastName().length() > 0
                && client.getDob() != null && client.getIdCard() > 0);
    }

    public BigDecimal getTotal(Client client) {
        List<Account> accountList = accountService.findByClient(client);
        BigDecimal total = new BigDecimal(0);
        for (Account account:accountList
             ) {
            total = total.add(account.getAssets());
        }
        return total;
    }
}
