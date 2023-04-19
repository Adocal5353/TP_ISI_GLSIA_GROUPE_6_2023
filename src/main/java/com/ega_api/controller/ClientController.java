package com.ega_api.controller;

import com.ega_api.models.Client;
import com.ega_api.service.ClientService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

}
