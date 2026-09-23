package com.labo.labo.service;

import com.labo.labo.dto.GammeDTO;
import com.labo.labo.dto.MarqueDTO;
import com.labo.labo.entity.Gamme;
import com.labo.labo.entity.Marque;
import com.labo.labo.factory.GammeFactory;
import com.labo.labo.factory.MarqueFactory;
import com.labo.labo.repository.MarqueRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static com.labo.labo.entity.QIngredient.ingredient;

@Service
public class MarqueService {

    @Autowired
   MarqueRepository marqueRepository;

    public Marque createMarque(Marque gamme) {
        return marqueRepository.save(gamme);
    }

    public List<Marque> getAllMarques() {
        return marqueRepository.findAll();
    }

    public Optional<Marque> getMarque (long id){
        return marqueRepository.findById(id);
    }

    public Marque updateMarque(Long id, Marque marqueDetails) {
        Marque marque = marqueRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Marque non trouvée avec l'id: " + id));
        marque.setNom(marqueDetails.getNom());
        return marqueRepository.save(marque);
    }

    public void deleteMarque(Long id) {
        marqueRepository.deleteById(id);
    }

//    public MarqueDTO save(MarqueDTO marqueDTO) {
//        log.debug("Request to save marque: {}", marqueDTO);
//        Marque marque = MarqueFactory.marqueDTOToMarque(marqueDTO);
//        Marque existingMarque=marqueRepository.findByNom(marque.getNom()); //findById return optional par défaut
//        if(existingMarque!=null)
//        { throw new IllegalStateException("marque existe !");}
//        marque = marqueRepository.save(marque);
//        return MarqueFactory.marqueToMarqueDTO(marque);
//    }
//
//    public MarqueDTO update(MarqueDTO marqueDTO) {
//        log.debug("Request to update marque: {}", marqueDTO);
//        Marque marque = MarqueFactory.marqueDTOToMarque(marqueDTO);
//        Marque inBase=marqueRepository.findByNom(marque.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("marque n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//        //Marque inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setNom(marqueDTO.getNom());
//        marque = marqueRepository.save(marque);
//        return MarqueFactory.marqueToMarqueDTO(marque);
//    }
//
//    @Transactional(readOnly = true)
//    public MarqueDTO getMarqueById(Long id) { //return dto
//        log.debug("Request to get marque: {}", id);
//        Optional<Marque> marqueOpt = marqueRepository.findById(id);
//        if(marqueOpt.isEmpty())
//        { throw new IllegalStateException("marque n'existe pas !");}
//        Marque marque=marqueOpt.get();
//        return MarqueFactory.marqueToMarqueDTO(marque);
//    }
//
//    @Transactional(readOnly = true)
//    public Marque getMarque(Long id) {   //return entité
//        log.debug("Request to get marque: {}", id);
//        Optional<Marque> marqueOpt = marqueRepository.findById(id);
//        if(marqueOpt.isEmpty())
//        { throw new IllegalStateException("marque n'existe pas !");}
//        return marqueOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<MarqueDTO> getAll() {
//        log.debug("Request to get All marque");
//        Collection<Marque> result = marqueRepository.findAll();
//        return MarqueFactory.marqueTomarqueDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public MarqueDTO getMarqueByNom(String nom) { //return dto
//        log.debug("Request to get Marque: {}", nom);
//        Marque marque = marqueRepository.findByNom(nom);
//        if(marque==null)
//        { throw new IllegalStateException("Marque n'existe pas !");}
//        return MarqueFactory.marqueToMarqueDTO(marque);
//    }
//    public void delete(Long id) {
//        log.debug("Request to delete marque: {}", id);
//        Optional<Marque> marqueOpt = marqueRepository.findById(id);
//        if(marqueOpt.isEmpty())
//        { throw new IllegalStateException("marque n'existe pas !");}
//        marqueRepository.deleteById(id);
//    }
//
//    /*private void checkmarqueExists(String titre) {
//        if (marqueRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("marque with titre " + titre + " already exists");
//        }*/
}
