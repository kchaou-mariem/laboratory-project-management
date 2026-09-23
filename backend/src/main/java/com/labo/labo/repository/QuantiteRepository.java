package com.labo.labo.repository;

import com.labo.labo.entity.CleQuantite;
import com.labo.labo.entity.Quantite;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuantiteRepository extends JpaRepository<Quantite, CleQuantite> {
}
