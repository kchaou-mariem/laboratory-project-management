package com.labo.labo.dto;

import com.labo.labo.entity.Formule;
import com.labo.labo.entity.Ingredient;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class QuantiteDTO {
    private Formule formule;
    private Ingredient ingredient;
    private Float quantite;
}
