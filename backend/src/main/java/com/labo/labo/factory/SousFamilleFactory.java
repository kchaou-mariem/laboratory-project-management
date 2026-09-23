package com.labo.labo.factory;

import com.labo.labo.dto.SousFamilleDTO;
import com.labo.labo.dto.TacheDTO;
import com.labo.labo.entity.Famille;
import com.labo.labo.entity.Projet;
import com.labo.labo.entity.SousFamille;
import com.labo.labo.entity.Tache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class SousFamilleFactory {
    public static SousFamilleDTO sousFamilleToSousFamilleDTO(SousFamille sousFamille) {
        SousFamilleDTO sousFamilleDTO = new SousFamilleDTO();
        sousFamilleDTO.setNom(sousFamille.getNom());

//        List<String> projets = new ArrayList<>();
//        if (sousFamille.getProjets() != null) {
//            for (Projet projet : sousFamille.getProjets()) {
//                projets.add(projet.getNom());
//            }
//        }
//        sousFamilleDTO.setProjets(projets);


//        List<String> familles = new ArrayList<>();
//        if (sousFamille.getFamilles() != null) {
//            for (Famille famille : sousFamille.getFamilles()) {
//                familles.add(famille.getNom());
//            }
//        }
//        sousFamilleDTO.setFamilles(familles);

        return sousFamilleDTO;
    }

    public static SousFamille sousFamilleDTOToSousFamille(SousFamilleDTO sousFamilleDTO) {
        SousFamille sousFamille = new SousFamille();
        sousFamille.setNom(sousFamilleDTO.getNom());

//        List<Projet> projets = new ArrayList<>();
//        for(String nomProjet : sousFamilleDTO.getProjets()) {
//            Projet projet = new Projet();
//            projet.setNom(nomProjet);
//            projets.add(projet);
//        }
//        sousFamille.setProjets(projets);

//        List<Famille> familles = new ArrayList<>();
//        for(String nomFamille : sousFamilleDTO.getFamilles()) {
//            Famille famille = new Famille();
//            famille.setNom(nomFamille);
//            familles.add(famille);
//        }
//        sousFamille.setFamilles(familles);



        return sousFamille;
    }

    public static Collection<SousFamilleDTO> sousFamilleToSousFamilleDTOs(Collection<SousFamille> sousFamilles) {
        List<SousFamilleDTO> sousFamillesDTO = new ArrayList<>();
        sousFamilles.forEach(x -> sousFamillesDTO.add(sousFamilleToSousFamilleDTO(x)));
        return sousFamillesDTO;
    }
}
