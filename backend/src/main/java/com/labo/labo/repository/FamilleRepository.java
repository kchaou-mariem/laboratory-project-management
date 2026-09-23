package com.labo.labo.repository;

import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Famille;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilleRepository extends JpaRepository<Famille,Long> {
    //Famille findByNom(String nom);
}
