package com.labo.labo.dto;

import com.labo.labo.entity.Equipement;
import com.labo.labo.entity.Tache;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class PlageHoraireEquipementTacheDTO {
    private Tache tache;
    private Equipement equipement;
    private LocalDateTime horaireD;
    private LocalDateTime horaireF;
}

