package com.labo.labo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class FamilleDTO {
    private String nom;
    private Collection<String> projets;
    private Collection<String> sousFamilles;
    private Collection<String> gammes;


}
