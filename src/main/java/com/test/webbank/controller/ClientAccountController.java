package com.test.webbank.controller;


import com.test.webbank.entity.Account;
import com.test.webbank.entity.Client;
import com.test.webbank.entity.Transaction;
import com.test.webbank.model.TransactionModel;
import com.test.webbank.service.AccountService;
import com.test.webbank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import java.math.BigDecimal;
import java.util.List;


@Controller
public class ClientAccountController {

    @Autowired
    ClientService clientService;

    @Autowired
    AccountService accountService;

    @Autowired
    TransactionModel transactionModel;

    @RequestMapping(value = "/client_account/{clientId}", method = RequestMethod.GET)
    public String getAllAccount(@PathVariable(name = "clientId") long clientId, Model model) {
        Client client = clientService.findById(clientId);
        List<Account> accountList = accountService.findByClient(client);
        model.addAttribute(client);
        model.addAttribute(accountList);
        return "ClientAccount";
    }

    @RequestMapping(value = "/client_account/add", method = RequestMethod.POST)
    public String addAccount(@RequestParam(name = "id") long clientId, Model model) {
        Client client = clientService.findById(clientId);
        Account account = new Account(client);
        accountService.saveAndFlush(account);
        return "redirect:/client_account/" + client.getId();
    }

    @RequestMapping(value = "/client_account", method = RequestMethod.POST)
    public String makeTransaction(@RequestParam("sender_id") Long senderAccNum
                                    , @RequestParam("recipient_id") Long recipientAccNum
                                    , @RequestParam("amount") String cash) {
        BigDecimal cashAmount = new BigDecimal(cash);
        Account sender = accountService.findByAccountNumber(senderAccNum),
                recipient = accountService.findByAccountNumber(recipientAccNum);
        if (sender != null && recipient != null) {
            transactionModel.makeTransaction(sender, recipient, cashAmount);
            return "redirect:/client_account/" + sender.getClient().getId();
        } else {
            return "WrongAccNum";
        }
    }

    @RequestMapping(value = "/client_account/fund", method = RequestMethod.POST)
    public String fundAccount(@RequestParam("recipient_id") Long recipientAccNum
                    , @RequestParam("amount") String cash) {
        BigDecimal cashAmount = new BigDecimal(cash);
        Account recipient = accountService.findByAccountNumber(recipientAccNum);
        if (recipient != null) {
            transactionModel.fund(recipient, cashAmount);
            return "redirect:/client_account/" + recipient.getClient().getId();
        } else {
            return "WrongAccNum";
        }
    }

    @RequestMapping(value = "/client_account/withdraw", method = RequestMethod.POST)
    public String withdrawFunds(@RequestParam("sender_id") Long senderAccNum
            , @RequestParam("amount") String cash) {
        BigDecimal cashAmount = new BigDecimal(cash);
        Account sender = accountService.findByAccountNumber(senderAccNum);
        if (sender != null && sender.getAssets().compareTo(cashAmount) >=0) {
            transactionModel.withdraw(sender, cashAmount);
            return "redirect:/client_account/" + sender.getClient().getId();
        } else if (sender.getAssets().compareTo(cashAmount) < 0){
            return "InsufficientFunds";
        } else {
            return "WrongAccNum";
        }
    }

}
