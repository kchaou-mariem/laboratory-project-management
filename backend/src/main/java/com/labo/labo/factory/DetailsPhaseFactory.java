package com.labo.labo.factory;

import com.labo.labo.dto.DetailsPhaseDTO;
import com.labo.labo.entity.DetailsPhase;
import com.labo.labo.entity.Tache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DetailsPhaseFactory {
//    public static DetailsPhaseDTO phaseToPhaseDTO(DetailsPhase detailsPhase) {
//        DetailsPhaseDTO detailsPhaseDTO = new DetailsPhaseDTO();
//        detailsPhaseDTO.setNom(detailsPhase.getNom());
//        detailsPhaseDTO.setDateDebut(detailsPhase.getDateDebut());
//        detailsPhaseDTO.setDateFin(detailsPhase.getDateFin());
//        detailsPhaseDTO.setTempsEstime(detailsPhase.getTempsEstime());
//        detailsPhaseDTO.setTempsRealise(detailsPhase.getTempsRealise());
//        detailsPhaseDTO.setTempsRealiseTotal(detailsPhase.getTempsRealiseTotal());
//        detailsPhaseDTO.setPourcentageTpsRealise(detailsPhase.getPourcentageTpsRealise());
//        detailsPhaseDTO.setStatut(detailsPhase.getStatut());

//        List<String> sujetTaches = new ArrayList<>();
//        for(Tache tache : detailsPhase.getTaches()) {
//            sujetTaches.add(tache.getSujet());
//        }
//        detailsPhaseDTO.setTaches(sujetTaches);



        /*List<String> formules = new ArrayList<>();
        for(Formule formule : detailsPhase.getFormules()) {
            formules.add(formule.get);
        }
        detailsPhaseDTO.setProjets(nomsProjets);*/

//        return detailsPhaseDTO;
//    }
}

//    public static DetailsPhase phaseDTOToPhase(DetailsPhaseDTO detailsPhaseDTO) {
//        DetailsPhase detailsPhase = new DetailsPhase();
//        detailsPhase.setNom(detailsPhaseDTO.getNom());
//        detailsPhase.setDateDebut(detailsPhaseDTO.getDateDebut());
//        detailsPhase.setDateFin(detailsPhaseDTO.getDateFin());
//        detailsPhase.setTempsEstime(detailsPhaseDTO.getTempsEstime());
//        detailsPhase.setTempsRealiseTotal(detailsPhaseDTO.getTempsRealiseTotal());
//        detailsPhase.setTempsRealise(detailsPhaseDTO.getTempsRealise());
//        detailsPhase.setPourcentageTpsRealise(detailsPhaseDTO.getPourcentageTpsRealise());
//        detailsPhase.setStatut(detailsPhaseDTO.getStatut());
//

//        List<Tache> taches = new ArrayList<>();
//        for(String sujetTache : detailsPhaseDTO.getTaches()) {
//            Tache tache = new Tache();
//            tache.setSujet(sujetTache);
//            taches.add(tache);
//        }
//        detailsPhase.setTaches(taches);
//        return detailsPhase;
//    }

//    public static Collection<DetailsPhaseDTO> phaseToPhaseDTOs(Collection<DetailsPhase> detailsPhases) {
//        List<DetailsPhaseDTO> phasesDTO = new ArrayList<>();
//        detailsPhases.forEach(x -> phasesDTO.add(phaseToPhaseDTO(x)));
//        return phasesDTO;
//    }


