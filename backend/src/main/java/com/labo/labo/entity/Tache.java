package com.labo.labo.entity;

import com.labo.labo.enumeration.Statut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Tache implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="date_debut")
    private LocalDate dateDebut;
    @Column(name="date_fin")
    private LocalDate dateFin;
    @Column(name="sujet")
    private String sujet;
    @Column(name="description")
    private String description;
    @Column(name="temps_Estime")
    private int tempsEstime;
    @Column(name="temps_realise")
    private int tempsRealise;
    @Column(name="temps_realise_total")
    private int tempsRealiseTotal; //se calcule automatiquement
    @Column(name="%realise")
    private int pourcentageTpsRealise; //se calcule automatiquement
   // @Enumerated(EnumType.STRING)
    @Column(name="statut")
    private Statut statut;
    @ManyToOne
    //@JoinColumn(name = "Phase")
    private DetailsPhase detailsPhase;
//    @ManyToOne(cascade = {CascadeType.PERSIST,CascadeType.MERGE})
//    @JoinColumn(name = "projet")
//    private Projet projet;
//    @ManyToMany(mappedBy = "taches")
//    private Collection<Equipement> equipements=new ArrayList<>();
//    @ManyToMany(mappedBy = "taches")
//    private Collection<Compte> comptes=new ArrayList<>();

    public Tache(LocalDate dateDebut, LocalDate dateFin, String sujet, String description, int tempsEstime, int tempsRealise, int tempsRealiseTotal, int pourcentageTpsRealise, Statut statut, DetailsPhase detailsPhase, Projet projet, Collection<Equipement> equipements, Collection<Compte> comptes) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sujet = sujet;
        this.description = description;
        this.tempsEstime = tempsEstime;
        this.tempsRealise = tempsRealise;
        this.tempsRealiseTotal = tempsRealiseTotal;
        this.pourcentageTpsRealise = pourcentageTpsRealise;
        this.statut = statut;
//        this.detailsPhase = detailsPhase;
//        this.projet = projet;
//        this.equipements = equipements;
//        this.comptes = comptes;
    }

    public Tache(LocalDate dateDebut, LocalDate dateFin, String sujet, String description, int tempsEstime, int tempsRealise, int tempsRealiseTotal, int pourcentageTpsRealise, Statut statut, Projet projet, Collection<Equipement> equipements, Collection<Compte> comptes) {
        this.dateDebut = dateDebut;
        this.dateFin = dateFin;
        this.sujet = sujet;
        this.description = description;
        this.tempsEstime = tempsEstime;
        this.tempsRealise = tempsRealise;
        this.tempsRealiseTotal = tempsRealiseTotal;
        this.pourcentageTpsRealise = pourcentageTpsRealise;
        this.statut = statut;
//        this.projet = projet;
//        this.equipements = equipements;
//        this.comptes = comptes;
    }
}
