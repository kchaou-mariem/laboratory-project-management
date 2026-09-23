package com.labo.labo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labo.labo.enumeration.StatutPhase;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data //setters and getters
//@AllArgsConstructor
@NoArgsConstructor
@Entity
public class DetailsPhase implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
//    @Column(name="nom_phase",unique = true)
//    private String nom;
    @Column(name="date_debut")
    private LocalDate dateDebut;
    @Column(name="date_fin")
    private LocalDate dateFin;
    @Column(name="temps_Estime")
    private int tempsEstime;
    @Column(name="temps_realise")
    private int tempsRealise;
    @Column(name="temps_realise_total")
    private int tempsRealiseTotal; //se calcule automatiquement
    @Column(name="%realise")
    private int pourcentageTpsRealise; //se calcule automatiquement
    @Column(name="statut")
    //@Type(type="string")
    //@Enumerated(EnumType.STRING)
    private StatutPhase statut;
//    @ManyToOne
//    private Phase phase;
//    @ManyToMany(mappedBy = "detailsPhases",fetch = FetchType.EAGER)
//    private Collection<Formule>formules=new ArrayList<>();
    @JsonIgnore
    @OneToMany(mappedBy = "detailsPhase", fetch = FetchType.EAGER)
    private Collection<Tache> taches=new ArrayList<>();


    public DetailsPhase(String nom, LocalDate dateDebut, LocalDate dateFin, int tempsEstime, int tempsRealise, int tempsRealiseTotal, int pourcentageTpsRealise, StatutPhase statut, Collection<Formule> formules, Collection<Tache> taches, Phase phase) {
//        this.nom = nom;
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.tempsEstime = tempsEstime;
        this.tempsRealise = tempsRealise;
        this.tempsRealiseTotal = tempsRealiseTotal;
        this.pourcentageTpsRealise = pourcentageTpsRealise;
        this.statut = statut;
//        this.formules = formules;
//        this.taches = taches;
//        this.phase=phase;
    }


}
