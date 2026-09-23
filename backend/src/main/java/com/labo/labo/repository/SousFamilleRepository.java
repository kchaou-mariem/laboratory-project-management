package com.labo.labo.repository;

import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.SousFamille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface SousFamilleRepository extends JpaRepository<SousFamille,Long> {
    //SousFamille findByNom(String nom);
}
