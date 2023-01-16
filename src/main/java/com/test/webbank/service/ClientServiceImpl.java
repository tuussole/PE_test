package com.test.webbank.service;

import com.test.webbank.dao.ClientRepo;
import com.test.webbank.entity.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    ClientRepo clientRepo;

    @Override
    public Client saveAndFlush(Client client) {
        return clientRepo.saveAndFlush(client);
    }

    @Override
    public Client findById(long id) {
        return clientRepo.findById(id);
    }

    @Override
    public List<Client> findAll() {
        return clientRepo.findAll();
    }

    @Override
    public List<Client> findByDob(Date dob) {
        return clientRepo.findByDob(dob);
    }

    @Override
    public List<Client> findByFirstName(String firstName) {
        return clientRepo.findByFirstName(firstName);
    }

    @Override
    public List<Client> findByLastName(String lastName) {
        return clientRepo.findByLastName(lastName);
    }

    @Override
    public Client findByIdCard(long idCard) {
        return clientRepo.findByIdCard(idCard);
    }
}
