package com.labo.labo.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labo.labo.enumeration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Compte implements Serializable {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    @Column( unique=true)
    private String email;
    private String password;
    private String nom;
    private String prenom;
//    private LocalDate dateNaissance;
    private Role role;
    @JsonIgnore
    @ManyToMany
    private List<Projet> projets=new ArrayList<>();
//    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "comptes")
//    private Collection<Projet> projets=new ArrayList<>();
//    @ManyToMany
//    @JoinTable(name = "compte_tache",
//            joinColumns = @JoinColumn(name = "compte_id"),
//            inverseJoinColumns = @JoinColumn(name = "tache_id"))
//    private Collection <Tache> taches=new ArrayList<>();

    @Override
    public String toString() {
        return "Compte{" +
                "email='" + email + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Compte compte = (Compte) o;
        return email.equals(compte.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email);
    }
}
