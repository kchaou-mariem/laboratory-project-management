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
public class Fichier implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom")
    private String nom;
    //a_verifier
    @Column(name="chemin")
    private String chemin;
//    @ManyToOne(cascade = CascadeType.ALL)
//    @JoinColumn(name = "projet")
    //private Projet projet;
//    @ManyToMany(mappedBy = "fichiers")
//    private Collection<Document> documents=new ArrayList<>();
//    @ManyToMany(mappedBy = "fichiers")
//    private Collection<Annonce> annonces=new ArrayList<>();

}
