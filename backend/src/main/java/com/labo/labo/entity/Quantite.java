package com.labo.labo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Quantite implements Serializable {
    @EmbeddedId
    private CleQuantite idQuantite;
    @ManyToOne
    @MapsId("idFormule")
    private Formule formule;
    @ManyToOne
    @MapsId("idIngredient")
    private Ingredient ingredient;

    @Column(name="quantite")
    private Float quantite;

    @Override
    public String toString() {
        return "Quantite{" +
                "fprmule=" + formule +
                ", ingredient=" + ingredient +
                ", quantite=" + quantite +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantite quantite = (Quantite) o;
        return idQuantite.equals(quantite.idQuantite);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idQuantite);
    }
}
