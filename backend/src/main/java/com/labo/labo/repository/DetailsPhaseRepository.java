package com.labo.labo.repository;

import com.labo.labo.entity.DetailsPhase;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DetailsPhaseRepository extends JpaRepository<DetailsPhase,Long> {
//    DetailsPhase findByNom(String nom);
//
//    Collection<DetailsPhase> findByStatut(String statut);
}
