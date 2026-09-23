package com.labo.labo.service;

import com.labo.labo.entity.Formule;
import com.labo.labo.entity.Phase;
import com.labo.labo.entity.Projet;
import com.labo.labo.enumeration.Statut;
import com.labo.labo.repository.FormuleRepository;
import com.labo.labo.repository.PhaseRepository;
import com.labo.labo.repository.ProjetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class PhaseService {
    //private final Logger log = LoggerFactory.getLogger(PhaseService.class);

    @Autowired
    PhaseRepository phaseRepository;
    @Autowired
    FormuleRepository formuleRepository;
    @Autowired
    ProjetService projetSerive;
    @Autowired
    ProjetRepository projetRepository;

    public Phase createPhase(Phase phase, Long formuleId) {
        Formule formule=formuleRepository.findById(formuleId).get();
        phase.setFormule(formule);
        return phaseRepository.save(phase);
    }
    public Phase createPhaseFabrication(Phase phase, Long formuleId) {
        Formule formule=formuleRepository.findById(formuleId).get();
        phase.setFormule(formule);
        Phase newPhase = phaseRepository.save(phase);
        formule.setStatut(Statut.EN_COURS);
        formuleRepository.save(formule);
        // update project to in progress
        Projet projet=formule.getProjet();
        projet.setStatut(Statut.EN_COURS);
        projetRepository.save(projet);
        return newPhase;
    }


    public Phase createPhaseTest(Phase phase, Long formuleId) {
        Formule formule=formuleRepository.findById(formuleId).get();
        phase.setFormule(formule);
        Phase newPhase = phaseRepository.save(phase);
        formuleRepository.save(formule);
        return newPhase;
    }

    public Phase completePhaseFabrication(Long id){
        //Formule formule=formuleRepository.findById(formuleId).get();
        Phase phase=phaseRepository.findById(id).get();
        phase.setDateFin(LocalDate.now());
        phase.setStatut(Statut.TERMINE);
        phase = phaseRepository.save(phase);
        Formule formule=phase.getFormule();
        formuleRepository.save(formule);
        return phase;
    }
    public List<Phase> getAllPhases() {
        return phaseRepository.findAll();
    }

    public Optional<Phase> getPhase (long id){
        return phaseRepository.findById(id);
    }

    public Phase updatePhase(Long id, Phase phaseDetails) {
        Phase phase = phaseRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Phase non trouvée avec l'id: " + id));
        phase.setStatut(phaseDetails.getStatut());
        phase.setDateDebut(phaseDetails.getDateDebut());
        phase.setDateFin(phaseDetails.getDateFin());
        phase.setFromFabrication(phaseDetails.getFromFabrication());
        phase.setFromTest(phaseDetails.getFromTest());
        return phaseRepository.save(phase);
    }

    public void deletePhase(Long id) {
        phaseRepository.deleteById(id);
    }

    public Phase updateStatutPhase(Long id, String statut) {
        Phase phase=phaseRepository.findById(id).get();
        phase.setStatut(Statut.valueOf(statut));

        if(Statut.TERMINE.equals(Statut.valueOf(statut)) || Statut.REJETE.equals(Statut.valueOf(statut)) ){
            phase.setDateFin(LocalDate.now());
        }
        phaseRepository.save(phase);
        Formule formule=phase.getFormule();

            if (Statut.TERMINE.equals(Statut.valueOf(statut))){
                formule.setStatut(Statut.TERMINE);
            }else if (Statut.REJETE.equals(Statut.valueOf(statut))){
                formule.setStatut(Statut.REJETE);
            }
        formuleRepository.save(formule);

        return phase;
    }

    public Phase updatePhaseDescription(Long phaseId, String description){
        Phase phase=phaseRepository.findById(phaseId)
                .orElseThrow(() -> new IllegalArgumentException("Phase non trouvée avec l'id: " + phaseId));
phase.setDescription(description);
return phaseRepository.save(phase);
    }
}