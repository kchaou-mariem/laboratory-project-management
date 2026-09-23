package com.labo.labo.dto;

import com.labo.labo.enumeration.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
public class CompteDTO {
    private String login;
    private String numTlf;
    private String email;
    private Role role;
    private Collection<String>projets;
}
