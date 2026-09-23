package com.labo.labo.repository;

import com.labo.labo.entity.Tache;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.Collection;

@Repository
public interface TacheRepository extends JpaRepository<Tache,Long> {


//    Collection<Tache> findByStatut(String statut);
//    Collection<Tache>findByProjet_Nom(String nom);
//    Collection<Tache>findByDetailsPhase_Nom(String nom);
//    Collection<Tache>findByProjet_NomAndDetailsPhase_Nom(String nomProjet, String nomPhase );
//   // Collection<Tache> findByEquipements_NomAndPlageHoraire_HoraireDebAndPlageHoraire_HoraireFin(String nomEquipement, LocalDateTime horaireDebut, LocalDateTime horaireFin);
//
//    Collection<Tache> findByComptes_Login(String login);
//    Collection<Tache> findByComptes_LoginAndDetailsPhase_Nom(String login, String nom);
//
//
//    Tache findBySujet(String sujet);
}
