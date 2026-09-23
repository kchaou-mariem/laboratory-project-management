package com.labo.labo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Annonce implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="titre", unique = true)
    private String titre;
    @Column(name="description")
    private String description;

    @ManyToOne(cascade = CascadeType.ALL)
    private Projet projet;


//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "annonce_fichier",
//            joinColumns = @JoinColumn(name = "annonce_id"),
//            inverseJoinColumns = @JoinColumn(name = "fichier_id"))
//    private Collection<Fichier> fichiers=new ArrayList<>();


}
