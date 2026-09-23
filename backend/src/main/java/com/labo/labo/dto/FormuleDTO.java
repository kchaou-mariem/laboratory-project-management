package com.labo.labo.dto;

import com.labo.labo.enumeration.Statut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class FormuleDTO {
    private LocalDate dateCreation;
    private Statut statut;
    private List<String> marqueInspireDe;
    private String projet;
    private Collection<String> ingredients;
    private Collection<String> phases;
}
