package com.labo.labo.service;

import com.labo.labo.dto.EquipementDTO;
import com.labo.labo.dto.FamilleDTO;
import com.labo.labo.entity.*;
import com.labo.labo.factory.EquipementFactory;
import com.labo.labo.factory.FamilleFactory;
import com.labo.labo.repository.FamilleRepository;
import com.labo.labo.repository.FormuleIngredientRepository;
import com.labo.labo.repository.IngredientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FamilleService {
    //private final Logger log = LoggerFactory.getLogger(FamilleService.class);

    @Autowired
    FamilleRepository familleRepository;

    public  Famille createFamille( Famille famille) {
        return familleRepository.save(famille);
    }

    public List<Famille> getAllFamilles() {
        return familleRepository.findAll();
    }

    public Optional<Famille> getFamille (long id){
        return familleRepository.findById(id);
    }

    public Famille updateFamille(Long id, Famille familleDetails) {
        Famille famille = familleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Famille non trouvée avec l'id: " + id));
        famille.setNom(familleDetails.getNom());
        famille.setGammes(familleDetails.getGammes());
        return familleRepository.save(famille);
    }

    public void deleteFamille(Long id) {
        familleRepository.deleteById(id);
    }

//    public FamilleDTO save(FamilleDTO familleDTO) {
//        log.debug("Request to save famille: {}", familleDTO);
//        Famille famille = FamilleFactory.familleDTOToFamille(familleDTO);
//        Famille existingFamille=familleRepository.findByNom(famille.getNom()); //findById return optional par défaut
//        if(existingFamille!=null)
//        { throw new IllegalStateException("famille existe !");}
//        famille = familleRepository.save(famille);
//        return FamilleFactory.familleToFamilleDTO(famille);
//    }
//
//    public FamilleDTO update(FamilleDTO familleDTO) {
//        log.debug("Request to update famille: {}", familleDTO);
//        Famille famille = FamilleFactory.familleDTOToFamille(familleDTO);
//        Famille inBase=familleRepository.findByNom(famille.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("famille n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//       // Famille inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setNom(familleDTO.getNom());
//        famille = familleRepository.save(famille);
//        return FamilleFactory.familleToFamilleDTO(famille);
//    }
//
//    @Transactional(readOnly = true)
//    public FamilleDTO getFamilleById(Long id) { //return dto
//        log.debug("Request to get famille: {}", id);
//        Optional<Famille> familleOpt = familleRepository.findById(id);
//        if(familleOpt.isEmpty())
//        { throw new IllegalStateException("famille n'existe pas !");}
//        Famille famille=familleOpt.get();
//        return FamilleFactory.familleToFamilleDTO(famille);
//    }
//
//    @Transactional(readOnly = true)
//    public Famille getfamille(Long id) {   //return entité
//        log.debug("Request to get famille: {}", id);
//        Optional<Famille> familleOpt = familleRepository.findById(id);
//        if(familleOpt.isEmpty())
//        { throw new IllegalStateException("famille n'existe pas !");}
//        return familleOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<FamilleDTO> getAll() {
//        log.debug("Request to get All famille");
//        Collection<Famille> result = familleRepository.findAll();
//        return FamilleFactory.familleToFamilleDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public FamilleDTO getFamilleByNom(String nom) { //return dto
//        log.debug("Request to get Famille: {}", nom);
//        Famille famille = familleRepository.findByNom(nom);
//        if(famille==null)
//        { throw new IllegalStateException("Famille n'existe pas !");}
//        return FamilleFactory.familleToFamilleDTO(famille);
//    }
//    public void delete(Long id) {
//        log.debug("Request to delete famille: {}", id);
//        Optional<Famille> familleOpt = familleRepository.findById(id);
//        if(familleOpt.isEmpty())
//        { throw new IllegalStateException("famille n'existe pas !");}
//        familleRepository.deleteById(id);
//    }
//
//    /*private void checkfamilleExists(String titre) {
//        if (familleRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("famille with titre " + titre + " already exists");
//        }*/
}
