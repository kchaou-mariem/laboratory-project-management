package com.labo.labo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labo.labo.enumeration.Statut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Formule {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="date_creation",updatable = false)
    private LocalDate dateCreation;
    private int formuleIndex;
    @Column(name="marque_inspiré_de")
    private String marqueInspireDe;
    private String produitInspireDe;
    @Column(name="statut")
    private Statut statut;
    @OneToMany(mappedBy = "formule",fetch = FetchType.EAGER)
    private List<FormuleIngredient> formuleIngredients = new ArrayList<>();
    @ManyToOne
    private Projet projet;
    @OneToMany(mappedBy = "formule",fetch = FetchType.EAGER)
    private List<Phase> phases= new ArrayList<>();
    @Transient
    private Phase phaseFabrication;
    @Transient
    private Phase phaseTest;

//    public Formule(LocalDate dateCreation, String marqueInspireDe, String produitInspireDe, Statut statut, List<FormuleIngredient> formuleIngredients, Projet projet, List<Phase> phases) {
//        this.dateCreation = dateCreation;
//        this.marqueInspireDe = marqueInspireDe;
//        this.produitInspireDe = produitInspireDe;
//        this.statut = statut;
//        this.formuleIngredients = formuleIngredients;
//        this.projet = projet;
//        this.phases = phases;
//        this.phaseFabrication = this.phases.stream().filter(phase->phase.getFromFabrication() !=null ).findFirst().orElse(null);
//        this.phaseTest = this.phases.stream().filter(phase -> phase.getFromTest()).findFirst().orElse(null);
//    }

    //    @Column(name="phase_fabrication")
//    private Statut phaseFabrication;

//    @ElementCollection //persister une liste de chaînes (List<String>) dans une entité avec JPA / normelment column oblige jpa à stocker cette collection dans table distincte
//    @Column(name="num_alternative")
//    private int numAlternative;

//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "FormuleIngredient",
//            joinColumns = @JoinColumn(name = "formule_id"),
//            inverseJoinColumns = @JoinColumn(name = "ingredient_id"))
//    private Collection<Ingredient>ingredients=new ArrayList<>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "formule_phase",
//            joinColumns = @JoinColumn(name = "formule_id"),
//            inverseJoinColumns = @JoinColumn(name = "phase_id"))
//    private Collection<DetailsPhase> detailsPhases =new ArrayList<>();
}
