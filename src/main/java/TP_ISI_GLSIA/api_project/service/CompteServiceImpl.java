package TP_ISI_GLSIA.api_project.service;

import TP_ISI_GLSIA.api_project.models.Compte;
import TP_ISI_GLSIA.api_project.repository.ClientRepository;
import TP_ISI_GLSIA.api_project.repository.CompteRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class CompteServiceImpl implements CompteService{
    @Autowired
    private final CompteRepository compteRepository;
    @Autowired
    private final ClientRepository clientRepository;
    @Override
    public Compte create(Compte compte) {
        compte.setClient(clientRepository.findById(compte.getClient().getId()).orElseThrow(()-> new RuntimeException("Pas de client trouvé avec cet identifiant")));

        return compteRepository.save(compte);
    }

    @Override
    public List<Compte> read() {
        return compteRepository.findAll();
    }

    @Override
    public Compte edit(String numCompte, Compte compte) {
        return compteRepository.findById(numCompte).map(c->{
            c.setClient(clientRepository.findById(compte.getClient().getId()).orElseThrow(()->new RuntimeException("Client non trouvé")));
            c.setTypeCompte(compte.getTypeCompte());
            return compteRepository.save(c);
        }).orElseThrow(()->new RuntimeException("Compte non trouvé"));
    }

    @Override
    public String delete(String numCompte) {
        compteRepository.deleteById(numCompte);
        return "Compte supprimé";
    }

    @Override
    public String transferer(String numCompteDebit, String numCompteCredit, double solde) {
        Optional<Compte> compteDebit = compteRepository.findById(numCompteDebit);
        Optional<Compte> compteCredit = compteRepository.findById(numCompteCredit);
        if(compteCredit.isPresent() && compteDebit.isPresent()){
            Compte cc = compteCredit.get();
            Compte cd = compteDebit.get();
            if(cd.getSolde()-solde<0){
                throw new RuntimeException("Opération invalide. Le solde est trop grand.");
            } else{
                cd.setSolde(cd.getSolde()-solde);
                cc.setSolde(cc.getSolde()+solde);
                compteRepository.save(cc);
                compteRepository.save(cd);
                return "le transfert a bien été effectué";
            }

        }
        else {
            throw new RuntimeException("L'opération ne peut pas être exécuté car les deux comptes ne sont pas présents.");
        }
    }

    @Override
    public String crediter(String numCompte, double solde) throws ChangeSetPersister.NotFoundException {
        Optional<Compte> compteCredit = compteRepository.findById(numCompte);
        if(compteCredit.isPresent()){
         Compte cc=compteCredit.get();
         cc.setSolde(cc.getSolde()+solde);
         compteRepository.save(cc);
         return "Le compte a été crédité avec succès";

        }
        throw new ChangeSetPersister.NotFoundException();
    }

    @Override
    public String debiter(String numCompte, double solde) throws ChangeSetPersister.NotFoundException {
        Optional<Compte> compteDebit = compteRepository.findById(numCompte);
        if(compteDebit.isPresent()){
            Compte cd = compteDebit.get();
            if(cd.getSolde()-solde<0){
                throw new RuntimeException("Opération invalide");
            }
            else{
                cd.setSolde(cd.getSolde()-solde);
                compteRepository.save(cd);
                return "Le compte a bien été débité";
            }
        }
        throw new ChangeSetPersister.NotFoundException();
    }
}
