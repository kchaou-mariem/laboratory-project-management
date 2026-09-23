package com.labo.labo.entity;
import com.labo.labo.enumeration.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Utilisateur implements Serializable {
    //id de user normalment howa id mt3 compte //utilisateur zaama hérité ml compte!
   @Id
    private String nom;
    private String prenom;
    private Role role;


}
