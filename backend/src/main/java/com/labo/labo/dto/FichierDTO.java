package com.labo.labo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class FichierDTO {
    private String nom;
    private String chemin;
    private String projet;
    private Collection<String> documents;
    private Collection<String> annonces;

}
