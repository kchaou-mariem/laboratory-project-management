package com.labo.labo.factory;

import com.labo.labo.dto.CompteRoleDTO;
import com.labo.labo.dto.QuantiteDTO;
import com.labo.labo.entity.Quantite;
import com.labo.labo.entity.RoleCompte;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompteRoleFactory {
    public static CompteRoleDTO compteRoleToCompteRoleDTO (RoleCompte roleCompte) {
        CompteRoleDTO compteRoleDTO = new CompteRoleDTO();
//        compteRoleDTO.setNomProjet(roleCompte.getProjet().getNom());
//        compteRoleDTO.setLoginCompte(roleCompte.getCompte().getLogin());
        compteRoleDTO.setRole(roleCompte.getRole());
        return compteRoleDTO;
    }

    public static RoleCompte roleCompteDTOToRoleCompte(CompteRoleDTO compteRoleDTO) {
        RoleCompte roleCompte = new RoleCompte();
//        roleCompte.getCompte().setLogin(compteRoleDTO.getLoginCompte());
//        roleCompte.getProjet().setNom(compteRoleDTO.getNomProjet());
        roleCompte.setRole(compteRoleDTO.getRole());
        return roleCompte;
    }

    public static Collection<CompteRoleDTO> compteRoleToCompteRoleDTOs(Collection<RoleCompte> roleComptes) {
        List<CompteRoleDTO> roleComptesDTO = new ArrayList<>();
        roleComptes.forEach(x -> roleComptesDTO.add(compteRoleToCompteRoleDTO(x)));
        return roleComptesDTO;
    }
}
