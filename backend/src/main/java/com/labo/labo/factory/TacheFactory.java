package com.labo.labo.factory;

import com.labo.labo.dto.TacheDTO;
import com.labo.labo.entity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


public class TacheFactory {

//        public static TacheDTO tacheToTacheDTO(Tache tache) {
//            TacheDTO tacheDTO = new TacheDTO();
//            tacheDTO.setDateDebut(tache.getDateDebut());
//            tacheDTO.setDateFin(tache.getDateFin());
//            tacheDTO.setSujet(tache.getSujet());
//            tacheDTO.setDescription(tache.getDescription());
//            tacheDTO.setTempsEstime(tache.getTempsEstime());
//            tacheDTO.setTempsRealise(tache.getTempsRealise());
//            tacheDTO.setTempsRealiseTotal(tache.getTempsRealiseTotal());
//            tacheDTO.setPourcentageTpsRealise(tache.getPourcentageTpsRealise());
//            tacheDTO.setStatut(tache.getStatut());
//
//            if (tache.getDetailsPhase() != null) {
//                tacheDTO.setPhase(tache.getDetailsPhase().getNom());
//            }
//
//            List<String> equipements = new ArrayList<>();
//            for(Equipement equipement : tache.getEquipements()) {
//                equipements.add(equipement.getNom());
//            }
//            tacheDTO.setEquipements(equipements);
//            return tacheDTO;
//        }
//
//        public static Tache tacheDTOToTache(TacheDTO tacheDTO) {
//            Tache tache = new Tache();
//            tache.setDateDebut(tacheDTO.getDateDebut());
//            tache.setDateFin(tacheDTO.getDateFin());
//            tache.setSujet(tacheDTO.getSujet());
//            tache.setDescription(tacheDTO.getDescription());
//            tache.setTempsEstime(tacheDTO.getTempsEstime());
//            tache.setTempsRealiseTotal(tacheDTO.getTempsRealiseTotal());
//            tache.setTempsRealise(tacheDTO.getTempsRealise());
//            tache.setPourcentageTpsRealise(tacheDTO.getPourcentageTpsRealise());
//            tache.setStatut(tacheDTO.getStatut());
//
//            DetailsPhase detailsPhase = new DetailsPhase();
//            detailsPhase.setNom(tacheDTO.getPhase());
//            tache.setDetailsPhase(detailsPhase);
//
//            Projet projet = new Projet();
//            projet.setNom(tacheDTO.getProjet());
//            tache.setProjet(projet);
//
//            List<Equipement> equipements = new ArrayList<>();
//            for(String nomEquipement : tacheDTO.getEquipements()) {
//                Equipement equipement = new Equipement();
//                equipement.setNom(nomEquipement);
//                equipements.add(equipement);
//            }
//            tache.setEquipements(equipements);
//
//            return tache;
//        }
//
//        public static Collection<TacheDTO> tacheToTacheDTOs(Collection<Tache> taches) {
//            List<TacheDTO> tachesDTO = new ArrayList<>();
//            taches.forEach(x -> tachesDTO.add(tacheToTacheDTO(x)));
//            return tachesDTO;
//        }
    }


