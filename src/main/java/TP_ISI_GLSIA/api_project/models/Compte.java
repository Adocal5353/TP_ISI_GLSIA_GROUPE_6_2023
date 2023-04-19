package TP_ISI_GLSIA.api_project.models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.UUID;

@Entity
@Table(name = "compte")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Compte {
    @Id
    @GenericGenerator(name = "custom_num_compte",strategy = "com.ega_api.models.CompteNumGenerator")
    @GeneratedValue(generator = "custom_num_compte")
    @Column(length = 15)
    private String numCompte;
    private TypeCompte typeCompte;
    private Date dateCreation=Date.valueOf(LocalDate.now());
    private  double solde =0.0;
    @ManyToOne
    @JsonBackReference
    private Client client;

}
