package TP_ISI_GLSIA.api_project.repository;

import TP_ISI_GLSIA.api_project.models.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompteRepository extends JpaRepository<Compte, String> {
}
