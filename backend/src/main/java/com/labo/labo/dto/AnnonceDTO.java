package com.labo.labo.dto;

import com.labo.labo.entity.Fichier;
import com.labo.labo.entity.Projet;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class AnnonceDTO {
    private String titre;
    private String description;
    private String nomProjet;
    private Collection<String> fichiers;


}
