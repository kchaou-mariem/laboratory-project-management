package com.labo.labo.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.labo.labo.enumeration.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class RoleCompte implements Serializable {

        @GeneratedValue(strategy = GenerationType.IDENTITY)
        @Id
        private Long id;

        private Role role;
        @JsonIgnore
        @OneToMany(mappedBy = "role",cascade = CascadeType.ALL)
        private List<Compte> comptes=new ArrayList<>();
//
//        @EmbeddedId
//        private CleRoleCompte idRoleCompte;
//        @ManyToOne
//        @MapsId("idCompte")
//        private Compte compte;
//        @ManyToOne
//        @MapsId("idProjet")
//        private Projet projet;
//
//        @Column(name="role")
//        private Role role;
}
