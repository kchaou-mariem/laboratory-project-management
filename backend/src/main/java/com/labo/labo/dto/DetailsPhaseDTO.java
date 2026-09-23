package com.labo.labo.dto;

import com.labo.labo.enumeration.StatutPhase;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class DetailsPhaseDTO {
    private String nom;
    private LocalDate dateDebut;
    private LocalDate dateFin;
    private int tempsEstime;
    private int tempsRealise;
    private int tempsRealiseTotal;
    private int pourcentageTpsRealise;
    private StatutPhase statut;
    private Collection<String>formules;
    private Collection<String>taches;



}
