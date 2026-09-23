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
public class Equipement implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(name="nom_equipement", unique=true)
    private String nom;
    @Column(name="disponibilite")
    private Boolean estDisponible;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "equipement_tache",
            joinColumns = @JoinColumn(name = "equipement_id"),
            inverseJoinColumns = @JoinColumn(name = "tache_id"))
    private Collection<Tache> taches=new ArrayList<>();



}
