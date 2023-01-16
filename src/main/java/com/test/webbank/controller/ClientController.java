package com.test.webbank.controller;

import com.test.webbank.entity.Client;
import com.test.webbank.model.ClientModel;
import com.test.webbank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.math.BigDecimal;
import java.util.List;

@Controller
public class ClientController implements ErrorController{

    @Autowired
    ClientService clientService;

    @Autowired
    ClientModel clientModel;

    private static final String PATH = "/error";

    @Override
    public String getErrorPath() {
        return PATH;
    }

    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public String showClients(Model model) {
        List<Client> clientsList = clientService.findAll();
        BigDecimal total = new BigDecimal(0);
        model.addAttribute("clients", clientsList);
        model.addAttribute("client", new Client());
        model.addAttribute("total", total);
        model.addAttribute("clientModel", clientModel);
        return "Clients";
    }

    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public String addClient(@ModelAttribute("client") Client client) {
        clientService.saveAndFlush(client);
        return "redirect:/clients";
    }

    @RequestMapping(value = PATH)
    public String getError() {
        return "ClientError";
    }
}


