package com.ega_api.service;

import com.ega_api.models.Client;

import java.util.List;

public interface ClientService {
    public Client create(Client client);
    public List<Client> read();
    public Client edit(String id, Client client);
    public String delete(String id);
}
