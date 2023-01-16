package com.test.webbank.controller;

import com.test.webbank.entity.Transaction;
import com.test.webbank.service.TransactionService;
import groovy.util.IFileNameFinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.List;

@Controller
public class TransactionController {

    @Autowired
    TransactionService transactionService;

    @RequestMapping(value = "/transactions", method = RequestMethod.GET)
    public String getAllTransactions(Model model) {
        List<Transaction> transactions = transactionService.findAll();
        model.addAttribute("transactions", transactions);
        return "Transactions";
    }

    @RequestMapping(value = "/transactions/filter", method = RequestMethod.GET)
    public String getTransactionsByDate(Model model, @RequestParam(name = "from") Date from,
                                        @RequestParam(name = "to") Date to) {
        if (from != null && to != null) {
            List<Transaction> transactions = transactionService.findByDateOfTransaction(from, to);
            model.addAttribute("transactions", transactions);
        }
        return "Transactions";
    }
}
