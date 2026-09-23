package com.labo.labo.repository;

import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Marque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface MarqueRepository extends JpaRepository<Marque,Long> {
//    Marque findByNom(String nom);
}
