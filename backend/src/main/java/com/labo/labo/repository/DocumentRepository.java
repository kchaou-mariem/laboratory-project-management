package com.labo.labo.repository;

import com.labo.labo.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository

public interface DocumentRepository extends JpaRepository<Document,Long> {
//    Document findByNom(String nom);
//    Collection findByProjet_Nom(String nom );
//    Document findByProjetNomAndNom(String nomProjet, String nomDocument);
}
