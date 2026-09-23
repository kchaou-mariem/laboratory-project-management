package com.labo.labo.repository;

import com.labo.labo.entity.Equipement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EquipementRepository extends JpaRepository<Equipement,Long> {
//    Equipement findByNom(String nom);
//
//    Equipement findByEstDisponible(Boolean estDispo);
}
