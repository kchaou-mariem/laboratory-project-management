package com.labo.labo.repository;

import com.labo.labo.entity.Projet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

import java.util.Collection;
import java.util.List;

@Repository

public interface ProjetRepository extends JpaRepository<Projet,Long> {
   // Projet findByNom(String nom);
List<Projet> findByNomStartingWithIgnoreCase(String prefix);

   Optional<Projet> findByNom(String nom);
//    Collection<Projet> findByStatut(String statut);
//    Collection<Projet> findByComptes_Login(String login);
//    Collection<Projet> findBySousFamille_Nom(String nom);

}
