package com.ega_api.controller;

import com.ega_api.models.Compte;
import com.ega_api.service.CompteService;
import lombok.AllArgsConstructor;
import org.json.JSONException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.web.bind.annotation.*;
import org.json.JSONObject;

import java.util.List;

@RestController
@RequestMapping("/compte")
@AllArgsConstructor
public class CompteController  {
    private final CompteService compteService;

    @PostMapping("/create")
    public Compte create(@RequestBody Compte compte){
       return compteService.create(compte);
    }

    @GetMapping("/read")
    public List<Compte> read(){
        return compteService.read();
    }

    @PutMapping("/update/{num}")
    public Compte edit(@PathVariable String num,@RequestBody Compte compte){
        return compteService.edit(num,compte);
    }

    @DeleteMapping("/delete/{num}")
    public String delete(@PathVariable String num){
        return compteService.delete(num);
    }
    @PutMapping("/credit/{num}")
    public String crediter(@PathVariable String num, @RequestBody String body){
        double solde = 0.0;
        try{
            JSONObject json= new JSONObject(body);
            solde = json.getDouble("solde");
           return compteService.crediter(num,solde);

        } catch (NumberFormatException | ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/debit/{num}")
    public String debiter(@PathVariable String num, @RequestBody String body){
        double solde = 0.0;
        try{
            JSONObject json= new JSONObject(body);
            solde = json.getDouble("solde");
            return compteService.debiter(num,solde);

        } catch (NumberFormatException | ChangeSetPersister.NotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping("/transfer")
    public String tranferer(@RequestBody String requestBody){
        double solde = 0.0;
        String numDeb = null;
        String numCred = null;
        try{
            JSONObject json = new JSONObject(requestBody);
            solde = json.getDouble("solde");
            numDeb= json.getString("debiteur");
            numCred = json.getString("crediteur");
            if(numCred!= null && numDeb!=null){
                return compteService.transferer(numDeb,numCred,solde);
            }
            throw  new RuntimeException("opération impossible");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }
    }
}
