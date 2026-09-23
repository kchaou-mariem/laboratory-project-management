package com.labo.labo.repository;

import com.labo.labo.entity.Annonce;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnnonceRepository extends JpaRepository<Annonce,Long> {
//    Annonce findByTitre(String titre) ;
//    void deleteByTitre(String titre);
}
