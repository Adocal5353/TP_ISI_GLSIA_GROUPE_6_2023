package com.ega_api.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "client")
@Setter
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Client {
    @Id
    @GenericGenerator(name = "custom_id_client",strategy = "com.ega_api.models.ClientIdGenerator")
    @GeneratedValue(generator = "custom_id_client")
    private String id;
    private String nom;
    private String prenom;
    private Date dateNaiss;
    private TypeSexe sexe;
    private String adresse;
    private String nationalite;
    private String courriel;
    @OneToMany(mappedBy = "client")
    @JsonManagedReference
    private Collection<Compte> comptes;
}
