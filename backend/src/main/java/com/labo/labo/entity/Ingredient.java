package com.labo.labo.entity;

import com.labo.labo.enumeration.StatutIngredient;
import com.labo.labo.enumeration.Unite;
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
public class Ingredient implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String nomCommercial;
    private Unite unite;
    @Column(unique = true)
    private String INCI;
    private String fonction;
    @Column(unique = true)
    private String casN;



//    @ManyToMany(mappedBy = "ingredients")
//    private Collection<Formule>formules=new ArrayList<>();

    @Override
    public String toString() {
        return "Ingredient{" +
                "nomCommercial='" + nomCommercial + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ingredient that = (Ingredient) o;
        return nomCommercial.equals(that.nomCommercial);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nomCommercial);
    }
}
