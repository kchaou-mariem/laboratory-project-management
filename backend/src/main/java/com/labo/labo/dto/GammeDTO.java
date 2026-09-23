package com.labo.labo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class GammeDTO {
    private String nom;
    private Collection<String>projets;
    private Collection<String>familles;
    private Collection<String>marques;
}
