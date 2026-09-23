package com.labo.labo.factory;

import com.labo.labo.dto.EquipementDTO;
import com.labo.labo.entity.Equipement;
import com.labo.labo.entity.Fichier;
import com.labo.labo.entity.Tache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class EquipementFactory {
    public static EquipementDTO equipementToEquipementDTO(Equipement equipement) {
        EquipementDTO equipementDTO = new EquipementDTO();
        equipementDTO.setNom(equipement.getNom());
        equipementDTO.setEstDisponible(equipement.getEstDisponible());
        List<String> sujetTaches = new ArrayList<>();
        for(Tache tache : equipement.getTaches()) {
            sujetTaches.add(tache.getSujet());
        }
        equipementDTO.setTaches(sujetTaches);
        return equipementDTO;
    }

    public static Equipement equipementDTOToEquipement(EquipementDTO equipementDTO) {
        Equipement equipement = new Equipement();
        equipement.setNom(equipementDTO.getNom());
        equipement.setEstDisponible(equipementDTO.getEstDisponible());
        List<Tache> taches = new ArrayList<>();
        for(String sujetTache : equipementDTO.getTaches()) {
            Tache tache = new Tache();
            tache.setSujet(sujetTache);
            taches.add(tache);
        }
        equipement.setTaches(taches);
        return equipement;
    }

    public static Collection<EquipementDTO> equipementToEquipementDTOs(Collection<Equipement> equipements) {
        List<EquipementDTO> equipementsDTO = new ArrayList<>();
        equipements.forEach(x -> equipementsDTO.add(equipementToEquipementDTO(x)));
        return equipementsDTO;
    }

}
