package com.labo.labo.factory;

import com.labo.labo.dto.AnnonceDTO;
import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Fichier;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AnnonceFactory {

    public static AnnonceDTO annonceToAnnonceDTO(Annonce annonce) {
        AnnonceDTO annonceDTO = new AnnonceDTO();
        annonceDTO.setTitre(annonce.getTitre());
        annonceDTO.setDescription(annonce.getDescription());
        //annonceDTO.setNomProjet(annonce.getProjet().getNom());
//        List<String> nomsFichiers = new ArrayList<>();
//        for(Fichier fichier : annonce.getFichiers()) {
//            nomsFichiers.add(fichier.getNom());
//        }
//        annonceDTO.setFichiers(nomsFichiers);
        return annonceDTO;
    }

    public static Annonce annonceDTOToAnnonce(AnnonceDTO annonceDTO) {
        Annonce annonce = new Annonce();
        annonce.setTitre(annonceDTO.getTitre());
        annonce.setDescription(annonceDTO.getDescription());
      //  annonce.getProjet().setNom(annonceDTO.getNomProjet());
        /*Projet projet = new Projet();
        projet.setNom(annonceDTO.getNomProjet());
        annonce.setProjet(projet);*/

//        List<Fichier> fichiers = new ArrayList<>();
//        for(String nomFichier : annonceDTO.getFichiers()) {
//            Fichier fichier = new Fichier();
//            fichier.setNom(nomFichier);
//            fichiers.add(fichier);
//        }
//        annonce.setFichiers(fichiers);
        return annonce;
    }

    public static Collection<AnnonceDTO> annonceToAnnonceDTOs(Collection<Annonce> annonces) {
        List<AnnonceDTO> annoncesDTO = new ArrayList<>();
        annonces.forEach(x -> annoncesDTO.add(annonceToAnnonceDTO(x)));
        return annoncesDTO;
    }
}
