package com.labo.labo.service;


import com.labo.labo.dto.FichierDTO;
import com.labo.labo.dto.GammeDTO;
import com.labo.labo.entity.Fichier;
import com.labo.labo.entity.Gamme;
import com.labo.labo.entity.Ingredient;
import com.labo.labo.factory.FichierFactory;
import com.labo.labo.factory.GammeFactory;
import com.labo.labo.repository.GammeRepository;
import jakarta.persistence.Id;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class GammeService {
   @Autowired
   GammeRepository gammeRepository;
    public Gamme createGamme(Gamme gamme) {
        return gammeRepository.save(gamme);
    }

    public List<Gamme> getAllGammes() {
        return gammeRepository.findAll();
    }

    public Optional<Gamme> getGamme (long id){
        return gammeRepository.findById(id);
    }

    public Gamme updateGamme(Long id, Gamme gammeDetails) {
        Gamme gamme = gammeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Gamme non trouvée avec l'id: " + id));
        gamme.setNom(gammeDetails.getNom());
        gamme.setMarques(gammeDetails.getMarques());
        return gammeRepository.save(gamme);
    }

    public void deleteGamme(Long id) {
        gammeRepository.deleteById(id);
    }


//    public GammeDTO save(GammeDTO gammeDTO) {
//        log.debug("Request to save gamme: {}", gammeDTO);
//        Gamme gamme = GammeFactory.gammeDTOToGamme(gammeDTO);
//        Gamme existingGamme=gammeRepository.findByNom(gamme.getNom()); //findById return optional par défaut
//        if(existingGamme!=null)
//        { throw new IllegalStateException("gamme existe !");}
//        gamme = gammeRepository.save(gamme);
//        return GammeFactory.gammeToGammeDTO(gamme);
//    }
//
//    public GammeDTO update(GammeDTO gammeDTO) {
//        log.debug("Request to update gamme: {}", gammeDTO);
//        Gamme gamme = GammeFactory.gammeDTOToGamme(gammeDTO);
//       Gamme inBase = gammeRepository.findByNom(gamme.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("gamme n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//       //.. Gamme inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setNom(gammeDTO.getNom());
//        gamme = gammeRepository.save(gamme);
//        return GammeFactory.gammeToGammeDTO(gamme);
//    }
//
//    @Transactional(readOnly = true)
//    public GammeDTO getGammeById(Long id) { //return dto
//        log.debug("Request to get gamme: {}", id);
//        Optional<Gamme> gammeOpt = gammeRepository.findById(id);
//        if(gammeOpt.isEmpty())
//        { throw new IllegalStateException("gamme n'existe pas !");}
//        Gamme gamme=gammeOpt.get();
//        return GammeFactory.gammeToGammeDTO(gamme);
//    }
//
//    @Transactional(readOnly = true)
//    public Gamme getGamme(Long id) {   //return entité
//        log.debug("Request to get gamme: {}", id);
//        Optional<Gamme> gammeOpt = gammeRepository.findById(id);
//        if(gammeOpt.isEmpty())
//        { throw new IllegalStateException("gamme n'existe pas !");}
//        return gammeOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<GammeDTO> getAll() {
//        log.debug("Request to get All gamme");
//        Collection<Gamme> result = gammeRepository.findAll();
//        return GammeFactory.gammeTogammeDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public GammeDTO getGammeByNom(String nom) { //return dto
//        log.debug("Request to get Gamme: {}", nom);
//        Gamme gamme = gammeRepository.findByNom(nom);
//        if(gamme==null)
//        { throw new IllegalStateException("Gamme n'existe pas !");}
//        return GammeFactory.gammeToGammeDTO(gamme);
//    }
//    public void delete(Long id) {
//        log.debug("Request to delete gamme: {}", id);
//        Optional<Gamme> gammeOpt = gammeRepository.findById(id);
//        if(gammeOpt.isEmpty())
//        { throw new IllegalStateException("gamme n'existe pas !");}
//        gammeRepository.deleteById(id);
//    }
//
//    /*private void checkgammeExists(String titre) {
//        if (gammeRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("gamme with titre " + titre + " already exists");
//        }*/
}
