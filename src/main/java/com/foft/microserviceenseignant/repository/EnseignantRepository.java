package com.foft.microserviceenseignant.repository;

import com.foft.microserviceenseignant.modele.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EnseignantRepository extends JpaRepository<Enseignant, Integer> {
}
