package com.labo.labo.factory;

import com.labo.labo.dto.FamilleDTO;
import com.labo.labo.dto.SousFamilleDTO;
import com.labo.labo.entity.Famille;
import com.labo.labo.entity.Gamme;
import com.labo.labo.entity.Projet;
import com.labo.labo.entity.SousFamille;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FamilleFactory {
    public static FamilleDTO familleToFamilleDTO(Famille famille) {
        FamilleDTO familleDTO = new FamilleDTO();
        familleDTO.setNom(famille.getNom());

//        List<String> nomsProjets = new ArrayList<>();
//        for(Projet projet : famille.getProjets()) {
//            nomsProjets.add(projet.getNom());
//        }
//        familleDTO.setProjets(nomsProjets);

//        List<String> nomsGamme = new ArrayList<>();
//        for(Gamme gamme : famille.getGammes()) {
//            nomsGamme.add(gamme.getNom());
//        }
//        familleDTO.setGammes(nomsGamme);

//        List<String> nomsSousFamille = new ArrayList<>();
//        for(SousFamille sousFamille : famille.getSousFamilles()) {
//            nomsSousFamille.add(sousFamille.getNom());
//        }
//        familleDTO.setSousFamilles(nomsSousFamille);


        return familleDTO;
    }

    public static Famille familleDTOToFamille(FamilleDTO familleDTO) {
        Famille famille = new Famille();
        famille.setNom(familleDTO.getNom());
//        List<Projet> projets = new ArrayList<>();
//        for(String nomProjet : familleDTO.getProjets()) {
//            Projet projet = new Projet();
//            projet.setNom(nomProjet);
//            projets.add(projet);
//        }
//        famille.setProjets(projets);

//        List<Gamme> gammes = new ArrayList<>();
//        for(String nomGamme : familleDTO.getGammes()) {
//            Gamme gamme = new Gamme();
//            gamme.setNom(nomGamme);
//            gammes.add(gamme);
//        }
//            famille.setGammes(gammes);

//        List<SousFamille> sousFamilles = new ArrayList<>();
//        for(String nomSousFamille : familleDTO.getSousFamilles()) {
//            SousFamille sousFamille = new SousFamille();
//            sousFamille.setNom(nomSousFamille);
//            sousFamilles.add(sousFamille);
//        }
//        famille.setSousFamilles(sousFamilles);


        return famille;
    }

    public static Collection<FamilleDTO> familleToFamilleDTOs(Collection<Famille> familles) {
        List<FamilleDTO> famillesDTO = new ArrayList<>();
        familles.forEach(x -> famillesDTO.add(familleToFamilleDTO(x)));
        return famillesDTO;
    }
}
