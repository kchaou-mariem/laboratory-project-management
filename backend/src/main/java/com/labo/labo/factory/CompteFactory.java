package com.labo.labo.factory;

import com.labo.labo.dto.CompteDTO;
import com.labo.labo.entity.Compte;
import com.labo.labo.entity.Fichier;
import com.labo.labo.entity.Projet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CompteFactory {
//    public static CompteDTO compteToCompteDTO(Compte compte) {
//        CompteDTO compteDTO = new CompteDTO();
//        compteDTO.setLogin(compte.getLogin());
//        compteDTO.setEmail(compte.getEmail());
//        compteDTO.setNumTlf(compte.getNumTlf());
//        compteDTO.setRole(compte.getRole());
//        List<String> nomsProjets = new ArrayList<>();
//        for(Projet projet : compte.getProjets()) {
//            nomsProjets.add(projet.getNom());
//        }
//        compteDTO.setProjets(nomsProjets);
//        return compteDTO;
//    }
//
//    public static Compte compteDTOToCompte(CompteDTO compteDTO) {
//        Compte compte = new Compte();
//        compte.setLogin(compteDTO.getLogin());
//        compte.setEmail(compteDTO.getEmail());
//        compte.setNumTlf(compteDTO.getNumTlf());
//        compte.setRole(compteDTO.getRole());
//        List<Projet> projets = new ArrayList<>();
//        for(String nomProjet : compteDTO.getProjets()) {
//            Projet projet = new Projet();
//            projet.setNom(nomProjet);
//            projets.add(projet);
//        }
//        compte.setProjets(projets);
//        return compte;
//    }
//
//    public static Collection<CompteDTO> compteToCompteDTOs(Collection<Compte> comptes) {
//        List<CompteDTO> comptesDTO = new ArrayList<>();
//        comptes.forEach(x -> comptesDTO.add(compteToCompteDTO(x)));
//        return comptesDTO;
//    }
}
