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
public class Marque implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "nom")
    private String nom;
//    @ManyToMany(mappedBy = "marques")
//    private Collection<Gamme> gammes=new ArrayList<>();
//    @OneToMany(mappedBy = "marque",fetch = FetchType.EAGER)
//    private Collection<Projet>projets=new ArrayList<>();

    public Marque(String nom) {
        this.nom=nom;
    }
}
