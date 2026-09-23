package com.labo.labo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PlageHoraireEquipementTache implements Serializable {
    @EmbeddedId
    private ClePlageHoraireEquipementTache id;
    @ManyToOne
    @MapsId("idTache")
    private Tache tache;
    @ManyToOne
    @MapsId("idEquipement")
    private Equipement equipement;

    @Column(name="horaire_debut")
    private LocalDateTime horaireD;
    @Column(name="horaire_fin")
    private LocalDateTime horaireF;
}
