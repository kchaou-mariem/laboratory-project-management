package com.labo.labo.repository;

import com.labo.labo.entity.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository

public interface FichierRepository extends JpaRepository<Fichier,Long> {
//    Fichier findByNom(String nom);
//    Collection<Fichier> findByProjet_Nom(String nom);
//    Collection<Fichier> findByDocuments_Nom(String nom);
//    Collection<Fichier> findByAnnonces_Titre(String titre);

}
