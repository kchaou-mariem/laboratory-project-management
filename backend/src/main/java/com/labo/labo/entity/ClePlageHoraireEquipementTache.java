package com.labo.labo.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class ClePlageHoraireEquipementTache implements Serializable {
    private Long idTache;
    private Long idEquipement;
}
