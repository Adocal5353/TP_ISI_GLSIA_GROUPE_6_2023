package TP_ISI_GLSIA.api_project.repository;

import TP_ISI_GLSIA.api_project.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
}
