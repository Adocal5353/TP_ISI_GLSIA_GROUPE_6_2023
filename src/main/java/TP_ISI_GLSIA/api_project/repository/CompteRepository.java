package com.ega_api.repository;

import com.ega_api.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
