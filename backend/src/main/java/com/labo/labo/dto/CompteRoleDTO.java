package com.labo.labo.dto;

import com.labo.labo.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class CompteRoleDTO {
    private String loginCompte;
    private String nomProjet;
    private Role role;
}
