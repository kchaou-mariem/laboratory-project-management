package com.labo.labo.service;

import com.labo.labo.dto.DocumentDTO;
import com.labo.labo.dto.EquipementDTO;
import com.labo.labo.entity.Document;
import com.labo.labo.entity.Equipement;
import com.labo.labo.factory.DocumentFactory;
import com.labo.labo.factory.EquipementFactory;
import com.labo.labo.repository.EquipementRepository;
//import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class EquipementService {
    private final Logger log = LoggerFactory.getLogger(EquipementService.class);

    private final EquipementRepository equipementRepository;

    public EquipementService(EquipementRepository equipementRepository) {
        this.equipementRepository = equipementRepository;
    }

//    public EquipementDTO save(EquipementDTO equipementDTO) {
//        log.debug("Request to save equipement: {}", equipementDTO);
//        Equipement equipement = EquipementFactory.equipementDTOToEquipement(equipementDTO);
//        Equipement existingEquipement=equipementRepository.findByNom(equipement.getNom()); //findById return optional par défaut
//        if(existingEquipement!=null)
//        { throw new IllegalStateException("equipement existe !");}
//
//        equipement = equipementRepository.save(equipement);
//        return EquipementFactory.equipementToEquipementDTO(equipement);
//    }
//
//    public EquipementDTO update(EquipementDTO equipementDTO) {
//        log.debug("Request to update equipement: {}", equipementDTO);
//        Equipement equipement = EquipementFactory.equipementDTOToEquipement(equipementDTO);
//        Equipement inBase=equipementRepository.findByNom(equipement.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("equipement n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//        //Equipement inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setNom(equipementDTO.getNom());
//        inBase.setEstDisponible(equipementDTO.getEstDisponible());
//        equipement = equipementRepository.save(equipement);
//        return EquipementFactory.equipementToEquipementDTO(equipement);
//    }
//
//    @Transactional(readOnly = true)
//    public EquipementDTO getEquipementById(Long id) { //return dto
//        log.debug("Request to get equipement: {}", id);
//        Optional<Equipement> equipementOpt = equipementRepository.findById(id);
//        if(equipementOpt.isEmpty())
//        { throw new IllegalStateException("equipement n'existe pas !");}
//        Equipement equipement=equipementOpt.get();
//        return EquipementFactory.equipementToEquipementDTO(equipement);
//    }
//
//    @Transactional(readOnly = true)
//    public Equipement getdEquipement(Long id) {   //return entité
//        log.debug("Request to get equipement: {}", id);
//        Optional<Equipement> equipementOpt = equipementRepository.findById(id);
//        if(equipementOpt.isEmpty())
//        { throw new IllegalStateException("equipement n'existe pas !");}
//        return equipementOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<EquipementDTO> getAll() {
//        log.debug("Request to get All equipement");
//        Collection<Equipement> result = equipementRepository.findAll();
//        return EquipementFactory.equipementToEquipementDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public EquipementDTO getEquipementByNom(String nom) { //return dto
//        log.debug("Request to get Equipement: {}", nom);
//        Equipement equipement = equipementRepository.findByNom(nom);
//        if(equipement==null)
//        { throw new IllegalStateException("equipement n'existe pas !");}
//        return EquipementFactory.equipementToEquipementDTO(equipement);
//    }
//    /*@Transactional(readOnly = true)
//    public EquipementDTO getEquipementByEstDisponible(Boolean estDispo) { //return dto
//        log.debug("Request to get Equipement: {}", estDispo);
//        Equipement equipement = equipementRepository.findByEstDisponible(estDispo);
//        if(equipement==null)
//        { throw new IllegalStateException("equipement n'existe pas !");}
//        return EquipementFactory.equipementToEquipementDTO(equipement);
//    }*/
//
//    /*@Transactional(readOnly = true)
//    public List<EquipementDTO> getEquipementsByEstDisponible(boolean estDispo) { // return dto list
//        log.debug("Request to get Equipements by availability: {}", estDispo);
//
//        // Obtenez la liste des équipements en fonction de l'indicateur d'existence
//        List<Equipement> equipements = equipementRepository.findByEstDisponible(estDispo);
//
//        // Vérifiez si la liste des équipements est vide
//        if(equipements.isEmpty()) {
//            // Lancez une IllegalStateException si aucun équipement n'est trouvé
//            throw new IllegalStateException("Aucun équipement disponible n'a été trouvé !");
//        }
//
//        // Convertissez la liste des équipements en liste d'objets DTO
//        return equipements.stream()
//                .map(EquipementFactory::equipementToEquipementDTO)
//                .collect(Collectors.toList());
//    }*/
//
//    public void delete(Long id) {
//        log.debug("Request to delete equipement: {}", id);
//        Optional<Equipement> equipementOpt = equipementRepository.findById(id);
//        if(equipementOpt.isEmpty())
//        { throw new IllegalStateException("equipement n'existe pas !");}
//        equipementRepository.deleteById(id);
//    }
//
//    /*private void checkequipementExists(String titre) {
//        if (equipementRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("equipement with titre " + titre + " already exists");
//        }*/
}
