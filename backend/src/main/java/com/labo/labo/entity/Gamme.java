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
public class Gamme implements Serializable {
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Id
    private Long id;
    @Column(name = "nom")
    private String nom;
//    @OneToMany(mappedBy = "gamme",fetch = FetchType.EAGER)
//    private Collection<Projet>projets=new ArrayList<>();
//    @ManyToMany(mappedBy = "gammes")
//    private Collection<Famille>familles=new ArrayList<>();
//    @ManyToMany(fetch = FetchType.EAGER)
//    @JoinTable(name = "gamme_marque",
//            joinColumns = @JoinColumn(name = "gamme_id"),
//            inverseJoinColumns = @JoinColumn(name = "marque_id"))
//    private Collection<Marque>marques=new ArrayList<>();
@ManyToMany(fetch = FetchType.EAGER)
private Collection<Marque> marques = new ArrayList<>();
    public Gamme(String nom) {
        this.nom=nom;
    }
}
