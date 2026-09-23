package com.labo.labo.dto;

import com.labo.labo.enumeration.Statut;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class TacheDTO {
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private String sujet;
    private String description;
    private int tempsEstime;
    private int tempsRealise;
    private int tempsRealiseTotal;
    private int pourcentageTpsRealise;
    private Statut statut;
    private String phase;
    private String projet;
    private Collection<String>equipements;
}
