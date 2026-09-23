package com.labo.labo.repository;

import com.labo.labo.entity.Compte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompteRepository extends JpaRepository<Compte,Long> {
    Optional<Compte> findByEmail(String email);
//
//    Compte findByLogin(String login);
//    Collection<Compte> findByProjets_Nom(String nom);
//    Collection<Compte> findByRole(Role role);
//
//    Compte findByNumTlf(String numTlf);
}
