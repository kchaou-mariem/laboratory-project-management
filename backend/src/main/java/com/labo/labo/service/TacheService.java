package com.labo.labo.service;

import com.labo.labo.dto.DetailsPhaseDTO;
import com.labo.labo.dto.TacheDTO;
import com.labo.labo.entity.*;
//import com.labo.labo.factory.DetailsPhaseFactory;
import com.labo.labo.factory.TacheFactory;
import com.labo.labo.repository.TacheRepository;
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
public class TacheService {
   // private final Logger log = LoggerFactory.getLogger(TacheService.class);

    @Autowired
    TacheRepository tacheRepository;

    public Tache createTache(Tache tache) {
        return tacheRepository.save(tache);
    }

    public List<Tache> getAllTaches() {
        return tacheRepository.findAll();
    }

    public Optional<Tache> getTache (long id){
        return tacheRepository.findById(id);
    }

    public Tache updateTache(Long id, Tache tacheDetails) {
        Tache tache = tacheRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Tache non trouvée avec l'id: " + id));
        tache.setDateDebut(tacheDetails.getDateDebut());
        tache.setDateFin(tacheDetails.getDateFin());
        tache.setStatut(tacheDetails.getStatut());
        tache.setDescription(tacheDetails.getDescription());
        tache.setTempsEstime(tacheDetails.getTempsEstime());
        tache.setTempsRealise(tacheDetails.getTempsRealise());
        tache =tacheRepository.save(tache);
        CalculerTempsRealiseTotalAndPourcentage(tacheDetails);
        tache.setTempsRealiseTotal(tacheDetails.getTempsRealiseTotal());
        tache.setPourcentageTpsRealise(tacheDetails.getPourcentageTpsRealise());
        return tacheRepository.save(tache);
    }

    public void CalculerTempsRealiseTotalAndPourcentage(Tache tache){
        tache.setTempsRealiseTotal(tache.getTempsRealiseTotal()+ tache.getTempsRealise());
        tache.setPourcentageTpsRealise((tache.getTempsRealiseTotal()*100)/ tache.getTempsEstime());
    }

    public void deleteTache(Long id) {
        tacheRepository.deleteById(id);
    }

//    public TacheService(TacheRepository tacheRepository , DetailsPhaseService detailsPhaseService) {
//        this.tacheRepository = tacheRepository;
//        this.detailsPhaseService = detailsPhaseService;
//    }

//    public TacheDTO save(TacheDTO tacheDTO) {
//        log.debug("Request to save tache: {}", tacheDTO);
//        Tache tache = TacheFactory.tacheDTOToTache(tacheDTO);
//
//        if(tache.getDateFin().isBefore(tache.getDateDebut())) {
//            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début.");
//        }
//
//        // Vérifier si la tâche a une phase associée
//        if (tache.getDetailsPhase() != null) {
//            // Récupérer la phase existante par son nom
//            DetailsPhaseDTO existingPhase = detailsPhaseService.getDetailsPhaseByNom(tache.getDetailsPhase().getNom());
//
//            if (existingPhase != null) {
//                // Associer la phase existante à la tâche
//                tache.setDetailsPhase(DetailsPhaseFactory.phaseDTOToPhase(existingPhase));
//            } else {
//                // Si la phase n'existe pas, lever une exception ou traiter le cas selon votre logique métier
//                throw new IllegalArgumentException("La phase associée n'existe pas.");
//            }
//        } else {
//            // Si aucune phase n'est associée à la tâche, lever une exception ou traiter le cas selon votre logique métier
//            throw new IllegalArgumentException("La tâche doit être associée à une phase.");
//        }
//
//        if(tache.getDateFin().isBefore(tache.getDateDebut())) {
//            // Gérer l'erreur ici, par exemple, lever une exception ou afficher un message d'erreur
//            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début.");
//        }
//        if (tache.getDetailsPhase().getDateDebut().isAfter(tache.getDateDebut())){
//            throw new IllegalArgumentException("La date début de la tache doit être postérieure à la date debut de la phase.");
//        }
//        if (tache.getDetailsPhase().getDateFin().isBefore(tache.getDateFin())){
//            throw new IllegalArgumentException("La date fin de la phase doit être postérieure à la date fin de la tache.");
//        }
//        // Enregistrer la tâche
//        tache = tacheRepository.save(tache);
//        return TacheFactory.tacheToTacheDTO(tache);
//    }


//    public TacheDTO update(TacheDTO tacheDTO) {
//        log.debug("Request to update tache: {}", tacheDTO);
//        //Tache tache = TacheFactory.tacheDTOToTache(tacheDTO);
//        Tache inBase = tacheRepository.findBySujet(tacheDTO.getSujet());
//        if(inBase==null)
//        { throw new IllegalStateException("tache n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//       // Tache inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setDateDebut(tacheDTO.getDateDebut());
//        inBase.setDateFin(tacheDTO.getDateFin());
//        inBase.setSujet(tacheDTO.getSujet());
//        inBase.setStatut(tacheDTO.getStatut());
//        inBase.setTempsEstime(tacheDTO.getTempsEstime());
//        inBase.setTempsRealise(tacheDTO.getTempsRealise());
//        inBase.setDescription(tacheDTO.getDescription());

//        if(inBase.getDateFin().isBefore(inBase.getDateDebut())) {
//            // Gérer l'erreur ici, par exemple, lever une exception ou afficher un message d'erreur
//            throw new IllegalArgumentException("La date de fin doit être postérieure à la date de début.");
//        }
//        inBase = tacheRepository.save(inBase);
//        return TacheFactory.tacheToTacheDTO(inBase);
//    }

//    @Transactional(readOnly = true)
//    public TacheDTO getTacheById(Long id) { //return dto
//        log.debug("Request to get tache: {}", id);
//        Optional<Tache> tacheOpt = tacheRepository.findById(id);
//        if(tacheOpt.isEmpty())
//        { throw new IllegalStateException("tache n'existe pas !");}
//        Tache tache=tacheOpt.get();
//        return TacheFactory.tacheToTacheDTO(tache);
//    }

//    @Transactional(readOnly = true)
//    public Tache getTache(Long id) {   //return entité
//        log.debug("Request to get tache: {}", id);
//        Optional<Tache> tacheOpt = tacheRepository.findById(id);
//        if(tacheOpt.isEmpty())
//        { throw new IllegalStateException("tache n'existe pas !");}
//        return tacheOpt.get();
//    }

//    @Transactional(readOnly = true)
//    public Collection<TacheDTO> getAll() {
//        log.debug("Request to get All tache");
//        Collection<Tache> result = tacheRepository.findAll();
//        return TacheFactory.tacheToTacheDTOs(result);
//    }
   /* @Transactional(readOnly = true)
    public TacheDTO getTacheByNom(String nom) { //return dto
        log.debug("Request to get Tache: {}", nom);
        Tache tache = tacheRepository.findByNom(nom);
        if(tache==null)
        { throw new IllegalStateException("Tache n'existe pas !");}
        return TacheFactory.tacheToTacheDTO(tache);
    }*/
//    @Transactional(readOnly = true)
//    public Collection<TacheDTO> getTacheByStatut(String statut) { //return dto
//        log.debug("Request to get Tache: {}", statut);
//        Collection<Tache> taches = tacheRepository.findByStatut(statut);
//        if(taches==null)
//        { throw new IllegalStateException("Tache n'existe pas !");}
//        return TacheFactory.tacheToTacheDTOs(taches);
//    }

//    @Transactional(readOnly = true)
//    public Collection<TacheDTO> getTacheByNomProjetAndNomPhase(String projet, String phase) { //return dto
//        log.debug("Request to get Tache: {}", projet,phase);
//        Collection<Tache> taches = tacheRepository.findByProjet_NomAndDetailsPhase_Nom(projet,phase);
//        if(taches==null)
//        { throw new IllegalStateException("Tache n'existe pas !");}
//        return TacheFactory.tacheToTacheDTOs(taches);
//    }
//    @Transactional(readOnly = true)
//    public Collection<TacheDTO> getTacheByProjet(String projet) { //return dto
//        log.debug("Request to get Tache: {}", projet);
//        Collection<Tache> taches = tacheRepository.findByProjet_Nom(projet);
//        if(taches==null)
//        { throw new IllegalStateException("Tache n'existe pas !");}
//        return TacheFactory.tacheToTacheDTOs(taches);
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<TacheDTO> getTacheByPhase(String phase) { //return dto:
//        log.debug("Request to get Tache: {}", phase);
//        Collection<Tache> taches = tacheRepository.findByDetailsPhase_Nom(phase);
//        if(taches==null)
//        { throw new IllegalStateException("Tache n'existe pas !");}
//        return TacheFactory.tacheToTacheDTOs(taches);
//    }

   /* @Transactional(readOnly = true)
    public Collection<TacheDTO> getTacheByEquipementAndPlageHoraire(String equipement, LocalDateTime hd, LocalDateTime hf) { //return dto:
        log.debug("Request to get Tache: {}", equipement,hd,hf);
        Collection<Tache> taches = tacheRepository.findByEquipements_NomAndPlageHoraire_HoraireDebAndPlageHoraire_HoraireFin(equipement, hd, hf);
        if(taches==null)
        { throw new IllegalStateException("Tache n'existe pas !");}
        return TacheFactory.tacheToTacheDTOs(taches);
    }*/
//    @Transactional(readOnly = true)
//
//    public Collection<TacheDTO> getTacheByLoginCompte(String login) { //return dto:
//        log.debug("Request to get Tache: {}", login);
//        Collection<Tache> taches = tacheRepository.findByComptes_Login(login);
//        if(taches==null)
//        { throw new IllegalStateException("Tache n'existe pas !");}
//        return TacheFactory.tacheToTacheDTOs(taches);
//    }
//
//    public Collection<TacheDTO> getTacheByLoginCompteAndNomPhase(String login,String phase) { //return dto:
//        log.debug("Request to get Tache: {}", login,phase);
//        Collection<Tache> taches = tacheRepository.findByComptes_LoginAndDetailsPhase_Nom(login,phase);
//        if(taches==null)
//        { throw new IllegalStateException("Tache n'existe pas !");}
//        return TacheFactory.tacheToTacheDTOs(taches);
//    }
//
//    public void delete(Long id) {
//        log.debug("Request to delete tache: {}", id);
//        Optional<Tache> tacheOpt = tacheRepository.findById(id);
//        if(tacheOpt.isEmpty())
//        { throw new IllegalStateException("tache n'existe pas !");}
//        tacheRepository.deleteById(id);
//    }
    /*private void checktacheExists(String titre) {
        if (tacheRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("tache with titre " + titre + " already exists");
        }*/
}
