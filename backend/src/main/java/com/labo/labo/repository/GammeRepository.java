package com.labo.labo.repository;

import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Gamme;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface GammeRepository extends JpaRepository<Gamme,Long> {
//    Gamme findByNom(String nom);
}
