package com.labo.labo.repository;

import com.labo.labo.entity.Formule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.text.CollationElementIterator;
import java.util.Collection;
import java.util.List;

@Repository
public interface FormuleRepository extends JpaRepository<Formule,Long> {
//    Collection<Formule> findByStatut(String statut);
//    Collection<Formule> findByProjet_Nom(String nom);
//    Formule findByNumAlternative(int numAlternative);
      List<Formule> findFormulesByProjetId(Long id);
}
