package TP_ISI_GLSIA.api_project.service;

import TP_ISI_GLSIA.api_project.models.Compte;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface CompteService {
    Compte create(Compte compte);
    List<Compte> read();
    Compte edit(String numCompte, Compte compte);
    String delete(String numCompte);
    String transferer(String numCompteDebit,String numCompteCredit,double solde);
    String crediter(String numCompte,double solde) throws ChangeSetPersister.NotFoundException;
    String debiter(String numCompte,double solde) throws ChangeSetPersister.NotFoundException;

}
