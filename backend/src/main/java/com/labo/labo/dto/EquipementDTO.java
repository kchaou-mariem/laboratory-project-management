package com.labo.labo.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class EquipementDTO {
    private String nom;
    private Boolean estDisponible;
    private Collection<String> taches;

}
