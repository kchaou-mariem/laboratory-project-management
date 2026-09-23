package com.labo.labo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labo.labo.enumeration.Statut;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.labo.labo.entity.Formule;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Phase {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
   private LocalDate dateDebut;
   private LocalDate dateFin;
   private Statut statut;
   private Boolean fromFabrication;
   private Boolean fromTest;
   @JsonIgnore
   @ManyToOne
   private Formule formule;
   @Lob
   private String description;
}