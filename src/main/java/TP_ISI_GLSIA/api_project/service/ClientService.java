package TP_ISI_GLSIA.api_project.service;

import TP_ISI_GLSIA.api_project.models.Client;
import TP_ISI_GLSIA.api_project.models.Compte;

import java.util.Collection;
import java.util.List;

public interface ClientService {
    public Client create(Client client);
    public List<Client> read();
    public Client edit(String id, Client client);
    public String delete(String id);
    public Collection<Compte> ClientComptes(String id);

}
