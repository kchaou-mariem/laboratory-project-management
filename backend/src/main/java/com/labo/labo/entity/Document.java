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
public class Document implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom")
    private String nom;
    @Column(name="description")
    private String description;
    //@ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "projet")
//
//
//    private Projet projet;
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "document_fichier",
//            joinColumns = @JoinColumn(name = "document_id"),
//            inverseJoinColumns = @JoinColumn(name = "fihcier_id"))
//    private Collection<Fichier> fichiers=new ArrayList<>();
}
