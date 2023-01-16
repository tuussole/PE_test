package com.test.webbank.dao;

import com.test.webbank.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import java.sql.Date;
import java.util.List;

public interface ClientRepo extends JpaRepository<Client, Long> {

    Client saveAndFlush(Client client);

    Client findById(long id);

    List<Client> findAll();

    List<Client>findByDob(Date dob);

    List<Client>findByFirstName(String firstName);

    List<Client> findByLastName(String lastName);

    Client findByIdCard(long idCard);
}
