package com.labo.labo.factory;
import com.labo.labo.dto.MarqueDTO;
import com.labo.labo.entity.Gamme;
import com.labo.labo.entity.Marque;
import com.labo.labo.entity.Projet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class MarqueFactory {
    public static MarqueDTO marqueToMarqueDTO(Marque marque) {
        MarqueDTO marqueDTO = new MarqueDTO();
        marqueDTO.setNom(marque.getNom());

//        List<String> nomsProjets = new ArrayList<>();
//        for(Projet projet : marque.getProjets()) {
//            nomsProjets.add(projet.getNom());
//        }
//        marqueDTO.setProjets(nomsProjets);

//        List<String> nomsGamme = new ArrayList<>();
//        for(Gamme gamme : marque.getGammes()) {
//            nomsGamme.add(gamme.getNom());
//        }
//        marqueDTO.setGammes(nomsGamme);

        return marqueDTO;
    }

    public static Marque marqueDTOToMarque(MarqueDTO marqueDTO) {
        Marque marque = new Marque();
        marque.setNom(marqueDTO.getNom());
//        List<Projet> projets = new ArrayList<>();
//        for(String nomProjet : marqueDTO.getProjets()) {
//            Projet projet = new Projet();
//            projet.setNom(nomProjet);
//            projets.add(projet);
//        }
//        marque.setProjets(projets);

//        List<Gamme> gammes = new ArrayList<>();
//        for(String nomGamme : marqueDTO.getGammes()) {
//            Gamme gamme = new Gamme();
//            gamme.setNom(nomGamme);
//            gammes.add(gamme);
//        }
//        marque.setGammes(gammes);
        return marque;
    }

    public static Collection<MarqueDTO> marqueTomarqueDTOs(Collection<Marque> marques) {
        List<MarqueDTO> marquesDTO = new ArrayList<>();
        marques.forEach(x -> marquesDTO.add(marqueToMarqueDTO(x)));
        return marquesDTO;
    }
}
