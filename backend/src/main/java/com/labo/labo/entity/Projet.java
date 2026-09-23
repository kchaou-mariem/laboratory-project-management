package com.labo.labo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labo.labo.enumeration.Statut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Projet implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom_projet", unique = true)
    private String nom;
    @Column(name="date_debut")
    private LocalDate dateDebut;
    @Column(name="date_fin")
    private LocalDate dateFin;
    //    @Column(name="realisation")
//    private Double realisation; //se calcule automatiquement
    @Lob
    @Column(name="description")
    private String description;
    @Column(name="par_qui")
    private String parQUI;
    @Column(name="comment")
    private String comment;
    @Column(name="statut")
    private Statut statut;
    @JsonIgnore
    @OneToMany(mappedBy = "projet",fetch = FetchType.EAGER)
    private List<Formule>formules=new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "projet_compte",
            joinColumns = @JoinColumn(name = "projet_id"),
            inverseJoinColumns = @JoinColumn(name = "compte_id"))
    private Collection<Compte> comptes=new ArrayList<>();

    private Long chefProjetId;


//    @Column(name="temps_Estime")
//    private int tempsEstime;
//    @Column(name="temps_realise")
//    private int tempsRealise;
//    @Column(name="temps_realise_total")
//    private int tempsRealiseTotal; //se calcule automatiquement

//    @Column(name="date_creation",updatable = false)
//    private LocalDate dateCreationProjet;
//    @ManyToMany(fetch = FetchType.EAGER)
//    private List<Compte> comptes=new ArrayList<>();
    //@Enumerated(EnumType.STRING)
    //@Column(name="statut",updatable = false)
    //en cascade, travail yssir en session pas BD
    //( cascade = {CascadeType.PERSIST, CascadeType.MERGE})
//    @ManyToOne//ki tssir modif en sousfamille tet3ada lel projet
//    private SousFamille sousFamille;
//    @ManyToOne
//    private Famille famille;
//    @ManyToOne
//    private Gamme gamme;
//    @ManyToOne
//    private Marque marque;

//    //possibilité d'enlever cascade et orpahnRemoval
//    @OneToMany(mappedBy = "projet",fetch =FetchType.EAGER,orphanRemoval = true ) //fetch.eager :parent ki iji, ijib maah les enfants ml BD automatiquement //orphanRemoval yomkn nfssa5ha
//    private Collection<Annonce>annonces=new ArrayList<>();
//    @OneToMany(mappedBy = "projet",fetch = FetchType.EAGER) //parent ki iji ijib maah les enfants ml BD automatiquement
//    private Collection<Document>documents=new ArrayList<>();
//    @OneToMany(mappedBy = "projet",fetch = FetchType.EAGER) //parent ki iji ijib maah les enfants ml BD automatiquement
//    private Collection<Fichier>fichiers=new ArrayList<>();
//


//    @OneToMany(mappedBy = "projet",fetch = FetchType.EAGER)
//    private Collection<Tache> taches=new ArrayList<>();

}
