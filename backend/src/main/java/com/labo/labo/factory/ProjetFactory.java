package com.labo.labo.factory;

import com.labo.labo.dto.ProjetDTO;
import com.labo.labo.entity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ProjetFactory {
    public static ProjetDTO projetToProjetDTO(Projet projet) {
        ProjetDTO projetDTO = new ProjetDTO();
        projetDTO.setNom(projet.getNom());
        projetDTO.setDateDebut(projet.getDateDebut());
        projetDTO.setDateFin(projet.getDateFin());
        projetDTO.setDescription(projet.getDescription());
//        projetDTO.setTempsEstime(projet.getTempsEstime());
//        projetDTO.setTempsRealise(projet.getTempsRealise());
//        projetDTO.setTempsRealiseTotal(projet.getTempsRealiseTotal());
        projetDTO.setStatut(projet.getStatut());
//        projetDTO.setPourcentageTpsRealise(projet.getPourcentageTpsRealise());
       // projetDTO.setParQUI(projet.getParQUI());
        projetDTO.setComment(projet.getComment());
//        projetDTO.setDateCreationProjet(projet.getDateCreationProjet());
        return projetDTO;
    }
//        if (projet.getFamille() != null) {
//            projetDTO.setFamille(projet.getFamille().getNom());
//        }
//
//        if (projet.getGamme() != null) {
//            projetDTO.setGamme(projet.getGamme().getNom());
//        }
//
//        if (projet.getMarque() != null) {
//            projetDTO.setMarque(projet.getMarque().getNom());
//        }
//
//        if (projet.getSousFamille() != null) {
//            projetDTO.setSousFamille(projet.getSousFamille().getNom());
//        }
//
//
//        List<String> nomsFichiers = new ArrayList<>();
//        for(Fichier fichier : projet.getFichiers()) {
//            nomsFichiers.add(fichier.getNom());
//        }
//        projetDTO.setFichiers(nomsFichiers);
//
//        List<String> nomsDocuments = new ArrayList<>();
//        for(Document document : projet.getDocuments()) {
//            nomsDocuments.add(document.getNom());
//        }
//        projetDTO.setDocuments(nomsDocuments);
//
//        List<String> nomsAnnonces = new ArrayList<>();
//        for(Annonce annonce : projet.getAnnonces()) {
//            nomsAnnonces.add(annonce.getTitre()+annonce.getDescription());
//        }
//        projetDTO.setAnnonces(nomsAnnonces);


//        List<String> comptes = new ArrayList<>();
//        for(Compte compte : projet.getComptes()) {
//            comptes.add(compte.getLogin());
//        }
//        projetDTO.setComptes(comptes);
//
//        return projetDTO;



    public static Projet projetDTOToProjet(ProjetDTO projetDTO) {
        Projet projet = new Projet();
        projet.setNom(projetDTO.getNom());
        projet.setStatut(projetDTO.getStatut());
        projet.setDateDebut(projetDTO.getDateDebut());
        projet.setDateFin(projetDTO.getDateFin());
        projet.setDescription(projetDTO.getDescription());
//        projet.setTempsEstime(projetDTO.getTempsEstime());
//        projet.setTempsRealiseTotal(projetDTO.getTempsRealiseTotal());
//        projet.setTempsRealise(projetDTO.getTempsRealise());
//        projet.setPourcentageTpsRealise(projetDTO.getPourcentageTpsRealise());
        //projet.setParQUI(projetDTO.getParQUI());
        /*projet.getFamille().setNom(projetDTO.getFamille());
        projet.getSousFamille().setNom(projetDTO.getSousFamille());
        projet.getMarque().setNom(projetDTO.getMarque());
        projet.getGamme().setNom(projetDTO.getGamme());*/
        projet.setComment(projetDTO.getComment());
//        projet.setDateCreationProjet(projetDTO.getDateCreationProjet());

//        Famille famille = new Famille();
//        famille.setNom(projetDTO.getFamille());
//        projet.setFamille(famille);
//
//        SousFamille sousFamille = new SousFamille();
//        sousFamille.setNom(projetDTO.getSousFamille());
//        projet.setSousFamille(sousFamille);
//
//        Marque marque = new Marque();
//        marque.setNom(projetDTO.getMarque());
//        projet.setMarque(marque);
//
//        Gamme gamme = new Gamme();
//        gamme.setNom(projetDTO.getGamme());
//        projet.setGamme(gamme);
//
        return projet;
    }

    public static Collection<ProjetDTO> projetsToProjetDTOs(Collection<Projet> projets) {
        List<ProjetDTO> projetsDTO = new ArrayList<>();
        projets.forEach(x -> projetsDTO.add(projetToProjetDTO(x)));
        return projetsDTO;
    }

}
