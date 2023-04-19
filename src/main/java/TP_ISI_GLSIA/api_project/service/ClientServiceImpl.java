package TP_ISI_GLSIA.api_project.service;

import TP_ISI_GLSIA.api_project.models.Client;
import TP_ISI_GLSIA.api_project.models.Compte;
import TP_ISI_GLSIA.api_project.repository.ClientRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
@AllArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    @Override
    public Client create(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> read() {
        return clientRepository.findAll();
    }

    @Override
    public Client edit(String id, Client client) {
        return clientRepository.findById(id).map(c->{
            c.setNom(client.getNom());
            c.setPrenom(client.getPrenom());
            c.setAdresse(client.getAdresse());
            c.setCourriel(client.getCourriel());
            c.setSexe(client.getSexe());
            return clientRepository.save(c);
        }).orElseThrow(()->new RuntimeException("Client non trouvé"));
    }

    @Override
    public String delete(String id) {
        clientRepository.deleteById(id);
        return "Client supprimé";
    }
    @Override
    public Collection<Compte> ClientComptes(String id) {
        return clientRepository.findById(id).get().getComptes();
    }
}
