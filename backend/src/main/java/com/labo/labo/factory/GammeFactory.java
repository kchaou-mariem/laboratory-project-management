package com.labo.labo.factory;



import com.labo.labo.dto.GammeDTO;
import com.labo.labo.entity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class GammeFactory {
//    public static GammeDTO gammeToGammeDTO(Gamme gamme) {
//        GammeDTO gammeDTO = new GammeDTO();
//        gammeDTO.setNom(gamme.getNom());
//        List<String> nomsProjets = new ArrayList<>();
//        for(Projet projet : gamme.getProjets()) {
//            nomsProjets.add(projet.getNom());
//        }
//        gammeDTO.setProjets(nomsProjets);
//
//        List<String> nomsMarques = new ArrayList<>();
//        for(Marque marque : gamme.getMarques()) {
//            nomsMarques.add(marque.getNom());
//        }
//        gammeDTO.setMarques(nomsProjets);
//
//        List<String> nomsFamille = new ArrayList<>();
//        for(Famille famille : gamme.getFamilles()) {
//            nomsFamille.add(famille.getNom());
//        }
//        gammeDTO.setFamilles(nomsFamille);
//
//        return gammeDTO;
//    }

//    public static Gamme gammeDTOToGamme(GammeDTO gammeDTO) {
//        Gamme gamme = new Gamme();
//        gamme.setNom(gammeDTO.getNom());
//
//
//        List<Marque> marques = new ArrayList<>();
//        for(String nomMarque : gammeDTO.getMarques()) {
//            Marque marque = new Marque();
//            marque.setNom(nomMarque);
//            marques.add(marque);
//        }
//        gamme.setMarques(marques);
//
//        List<Projet> projets = new ArrayList<>();
//        for(String nomProjet : gammeDTO.getProjets()) {
//            Projet projet = new Projet();
//            projet.setNom(nomProjet);
//            projets.add(projet);
//        }
//        gamme.setProjets(projets);
//
//        List<Famille> familles = new ArrayList<>();
//        for(String nomFamille : gammeDTO.getFamilles()) {
//            Famille famille = new Famille();
//            famille.setNom(nomFamille);
//            familles.add(famille);
//        }
//        gamme.setFamilles(familles);
//
//        return gamme;
//    }

//    public static Collection<GammeDTO> gammeTogammeDTOs(Collection<Gamme> gammes) {
//        List<GammeDTO> gammesDTO = new ArrayList<>();
//        gammes.forEach(x -> gammesDTO.add(gammeToGammeDTO(x)));
//        return gammesDTO;
//    }
}
