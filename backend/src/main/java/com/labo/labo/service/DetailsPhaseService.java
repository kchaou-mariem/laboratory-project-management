 package com.labo.labo.service;

import com.labo.labo.dto.DetailsPhaseDTO;
import com.labo.labo.entity.DetailsPhase;
//import com.labo.labo.factory.DetailsPhaseFactory;
import com.labo.labo.entity.Phase;
import com.labo.labo.repository.DetailsPhaseRepository;
//import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class DetailsPhaseService {
    private final Logger log = LoggerFactory.getLogger(DetailsPhaseService.class);

    @Autowired
    DetailsPhaseRepository detailsPhaseRepository;


    public DetailsPhase createDetailsPhase(DetailsPhase detailsPhase) {
        return detailsPhaseRepository.save(detailsPhase);
    }

    public List<DetailsPhase> getAllDetailsPhases() {
        return detailsPhaseRepository.findAll();
    }

    public Optional<DetailsPhase> getDetailsPhase (long id){
        return detailsPhaseRepository.findById(id);
    }

    public DetailsPhase updateDetailsPhase(Long id, DetailsPhase detailsPhaseDetails) {
        DetailsPhase detailsPhase = detailsPhaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DetailsPhase non trouvée avec l'id: " + id));
        detailsPhase.setDateDebut(detailsPhaseDetails.getDateDebut());
        detailsPhase.setDateFin(detailsPhaseDetails.getDateFin());
        detailsPhase.setStatut(detailsPhaseDetails.getStatut());
        detailsPhase.setTempsEstime(detailsPhaseDetails.getTempsEstime());
        detailsPhase.setTempsRealise(detailsPhaseDetails.getTempsRealise());
        detailsPhase =detailsPhaseRepository.save(detailsPhase);
        CalculerTempsRealiseTotalAndPourcentage(detailsPhaseDetails);
        detailsPhase.setTempsRealiseTotal(detailsPhaseDetails.getTempsRealiseTotal());
        detailsPhase.setPourcentageTpsRealise(detailsPhaseDetails.getPourcentageTpsRealise());
        return detailsPhaseRepository.save(detailsPhase);
    }
    public void CalculerTempsRealiseTotalAndPourcentage(DetailsPhase detailsPhase){
        detailsPhase.setTempsRealiseTotal(detailsPhase.getTempsRealiseTotal()+ detailsPhase.getTempsRealise());
        detailsPhase.setPourcentageTpsRealise((detailsPhase.getTempsRealiseTotal()*100)/ detailsPhase.getTempsEstime());
    }

    public void deleteDetailsPhase(Long id) {
        detailsPhaseRepository.deleteById(id);
    }

//    public DetailsPhaseService(DetailsPhaseRepository detailsPhaseRepository) {
//        this.detailsPhaseRepository = detailsPhaseRepository;
//    }

//    public DetailsPhaseDTO save(DetailsPhaseDTO detailsPhaseDTO) {
//        log.debug("Request to save detailsPhase: {}", detailsPhaseDTO);
//        DetailsPhase detailsPhase = DetailsPhaseFactory.phaseDTOToPhase(detailsPhaseDTO);
//        DetailsPhase existingDetailsPhase = detailsPhaseRepository.findByNom(detailsPhase.getNom()); //findById return optional par défaut
//        if(existingDetailsPhase !=null)
//        { throw new IllegalStateException("detailsPhase existe !");}
//        if(detailsPhase.getDateFin().isBefore(detailsPhase.getDateDebut())) {
//            // Gérer l'erreur ici, par exemple, lever une exception ou afficher un message d'erreur
//            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début.");
//        }

//if (detailsPhase.getDateDebut().isBefore(detailsPhase.getFormules()))
//
//        detailsPhase = detailsPhaseRepository.save(detailsPhase);
//        return DetailsPhaseFactory.phaseToPhaseDTO(detailsPhase);
//    }


//    public DetailsPhaseDTO update(DetailsPhaseDTO detailsPhaseDTO) {
//        log.debug("Request to update phase: {}", detailsPhaseDTO);
//        //DetailsPhase phase = DetailsPhaseFactory.phaseDTOToPhase(detailsPhaseDTO);
//       DetailsPhase inBase = detailsPhaseRepository.findByNom(detailsPhaseDTO.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("phase n'existe pas !");}
        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");


        //DetailsPhase inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setDateDebut(detailsPhaseDTO.getDateDebut());
//        inBase.setDateFin(detailsPhaseDTO.getDateFin());
//        inBase.setNom(detailsPhaseDTO.getNom());
//        inBase.setStatut(detailsPhaseDTO.getStatut());
//        inBase.setTempsEstime(detailsPhaseDTO.getTempsEstime());
//        inBase.setTempsRealise(detailsPhaseDTO.getTempsRealise());
//        inBase = detailsPhaseRepository.save(inBase);
//        CalculerTempsRealiseTotalAndPourcentage(detailsPhaseDTO);
//        inBase.setTempsRealiseTotal(detailsPhaseDTO.getTempsRealiseTotal());
//        inBase.setPourcentageTpsRealise(detailsPhaseDTO.getPourcentageTpsRealise());
//        if(inBase.getDateFin().isBefore(inBase.getDateDebut())) {
//            // Gérer l'erreur ici, par exemple, lever une exception ou afficher un message d'erreur
//            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début.");
//        }
//        inBase = detailsPhaseRepository.save(inBase);
//        return DetailsPhaseFactory.phaseToPhaseDTO(inBase);
//    }
//
//    @Transactional(readOnly = true)
//    public DetailsPhaseDTO getDetailsPhaseById(Long id) { //return dto
//        log.debug("Request to get detailsPhase: {}", id);
//        Optional<DetailsPhase> phaseOpt = detailsPhaseRepository.findById(id);
//        if(phaseOpt.isEmpty())
//        { throw new IllegalStateException("detailsPhase n'existe pas !");}
//        DetailsPhase detailsPhase =phaseOpt.get();
//        return DetailsPhaseFactory.phaseToPhaseDTO(detailsPhase);
//    }

//    @Transactional(readOnly = true)
//    public DetailsPhase getphase(Long id) {   //return entité
//        log.debug("Request to get phase: {}", id);
//        Optional<DetailsPhase> phaseOpt = detailsPhaseRepository.findById(id);
//        if(phaseOpt.isEmpty())
//        { throw new IllegalStateException("phase n'existe pas !");}
//        return phaseOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<DetailsPhaseDTO> getAll() {
//        log.debug("Request to get All phase");
//        Collection<DetailsPhase> result = detailsPhaseRepository.findAll();
//        return DetailsPhaseFactory.phaseToPhaseDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public DetailsPhaseDTO getDetailsPhaseByNom(String nom) { //return dto
//        log.debug("Request to get DetailsPhase: {}", nom);
//        DetailsPhase detailsPhase = detailsPhaseRepository.findByNom(nom);
//        if(detailsPhase ==null)
//        { throw new IllegalStateException("DetailsPhase n'existe pas !");}
//        return DetailsPhaseFactory.phaseToPhaseDTO(detailsPhase);
//    }
//    @Transactional(readOnly = true)
//    public Collection<DetailsPhaseDTO> getDetailsPhaseByStatut(String statut) { //return dto
//        log.debug("Request to get DetailsPhase: {}", statut);
//        Collection<DetailsPhase> detailsPhases = detailsPhaseRepository.findByStatut(statut);
//        if(detailsPhases ==null)
//        { throw new IllegalStateException("DetailsPhase n'existe pas !");}
//        return DetailsPhaseFactory.phaseToPhaseDTOs(detailsPhases);
//    }
//
//
//
//    public void CalculerTempsRealiseTotalAndPourcentage(DetailsPhaseDTO detailsPhaseDTO){
//        log.debug("Request to get calculate temps Realise total: {}", detailsPhaseDTO);
//        detailsPhaseDTO.setTempsRealiseTotal(detailsPhaseDTO.getTempsRealiseTotal()+ detailsPhaseDTO.getTempsRealise());
//        detailsPhaseDTO.setPourcentageTpsRealise((detailsPhaseDTO.getTempsRealiseTotal()*100)/ detailsPhaseDTO.getTempsEstime());
//
//    }
//
//    public void delete(Long id) {
//        log.debug("Request to delete phase: {}", id);
//        Optional<DetailsPhase> phaseOpt = detailsPhaseRepository.findById(id);
//        if(phaseOpt.isEmpty())
//        { throw new IllegalStateException("phase n'existe pas !");}
//        detailsPhaseRepository.deleteById(id);
//    }
//    /*public void delete(DetailsPhase dp) {
//        log.debug("Request to delete phase: {}", dp);
//        Optional<DetailsPhase> phaseOpt = detailsPhaseRepository.findById(dp.getIdDetailsPhase());
//        if(phaseOpt.isEmpty())
//        { throw new IllegalStateException("phase n'existe pas !");}
//        detailsPhaseRepository.deleteById(dp.getIdDetailsPhase());
//    }*/
//    public void delete(DetailsPhase detailsPhase) {
//        log.debug("Request to delete phase: {}", detailsPhase);
//        if (detailsPhase.getIdDetailsPhase() == null || !detailsPhaseRepository.existsById(detailsPhase.getIdDetailsPhase())) {
//            throw new IllegalStateException("phase n'existe pas !");
//        }
//        detailsPhaseRepository.delete(detailsPhase);
//    }


    /*private void checkphaseExists(String titre) {
        if (detailsPhaseRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("phase with titre " + titre + " already exists");
        }*/


}
