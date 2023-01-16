package com.test.webbank.service;

import com.test.webbank.entity.Client;
import java.sql.Date;
import java.util.List;

public interface ClientService {

    Client saveAndFlush(Client client);

    Client findById(long id);

    List<Client> findAll();

    List<Client>findByDob(Date dob);

    List<Client>findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    Client findByIdCard(long idCard);
}
