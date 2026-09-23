package com.labo.labo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class FormuleIngredient {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private Double quantite;
    @JsonIgnore
    @ManyToOne
    private Formule formule;
    @ManyToOne
    private Ingredient ingredient;





}
