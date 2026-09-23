package com.labo.labo.dto;


import com.labo.labo.enumeration.Statut;
import jakarta.persistence.PrePersist;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class ProjetDTO {
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int tempsEstime;
    private int tempsRealise;
    private int tempsRealiseTotal;
    private int pourcentageTpsRealise;
    private String description;
    private List<String> parQUI;
    private String comment;
    private LocalDate dateCreationProjet;
    private Statut statut;
    private String sousFamille;
    private String famille;
    private String gamme;
    private String marque;
    private List<String> documents;
    private List<String> fichiers;
    private List<String> annonces;
   // private List<String> formules;
    private List<String> comptes;
    private String detailsPhase;
    @PrePersist
    public void setDateCreationProjet() {
        this.dateCreationProjet = LocalDate.now();
    }


}
