package com.labo.labo.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class DocumentDTO {
    private String nom;
    private String description;
    private String nomProjet;
    private Collection<String > fichiers;

}
