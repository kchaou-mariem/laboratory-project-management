package com.labo.labo.repository;

import com.labo.labo.entity.Phase;
import com.labo.labo.enumeration.Statut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhaseRepository extends JpaRepository<Phase,Long> {
    //Phase findByNom(String nom);
    Phase findByStatut(Statut statut);
}
