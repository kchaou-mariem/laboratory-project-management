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
public class SousFamille implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "nom")
    private String nom;

//    @OneToMany(mappedBy = "sousFamille", fetch = FetchType.EAGER)
//    private Collection<Projet> projets = new ArrayList<>();

    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Famille> familles = new ArrayList<>();

    public SousFamille(String nom) {
        this.nom=nom;
    }
}