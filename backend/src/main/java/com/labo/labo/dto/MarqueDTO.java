package com.labo.labo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class MarqueDTO {
    private String nom;
    private Collection<String>gammes;
    private Collection<String>projets;
}
