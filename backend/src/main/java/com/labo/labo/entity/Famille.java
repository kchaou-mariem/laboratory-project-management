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
public class Famille implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "nom")
    private String nom;
//    @OneToMany(fetch = FetchType.EAGER,mappedBy = "famille")
//    private Collection<Projet> projets=new ArrayList<>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "famille_sousFamille",
//            joinColumns = @JoinColumn(name = "famille_id"),
//            inverseJoinColumns = @JoinColumn(name = "sousFamille_id"))
//    private Collection<SousFamille>sousFamilles=new ArrayList<>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "famille_gamme",
//            joinColumns = @JoinColumn(name = "famille_id"),
//            inverseJoinColumns = @JoinColumn(name = "gamme_id"))
//    private Collection<Gamme>gammes=new ArrayList<>();
@ManyToMany(fetch = FetchType.EAGER)
private Collection<Gamme> gammes = new ArrayList<>();

    public Famille(String nom) {
        this.nom=nom;
    }
}
