package TP_ISI_GLSIA.api_project.controller;

import TP_ISI_GLSIA.api_project.models.Client;
import TP_ISI_GLSIA.api_project.models.Compte;
import TP_ISI_GLSIA.api_project.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/client")
@AllArgsConstructor
public class ClientController {
    @Autowired
    private final ClientService clientService;
    @PostMapping("/create")
    public Client create(@RequestBody Client client){
        return clientService.create(client);
    }

    @GetMapping("/read")
    public List<Client> read(){
        return clientService.read();
    }
    @PutMapping("/update/{id}")
    public Client update(@PathVariable String id,@RequestBody Client client){
        return  clientService.edit(id,client);
    }

    @DeleteMapping("/delete/{id}")
    public String delete( @PathVariable  String id){
        return clientService.delete(id);
    }

    @GetMapping("/{id}/comptes")
    public Collection<Compte> ComptePerClient(@PathVariable String id){
        return clientService.ClientComptes(id);
    }

}
