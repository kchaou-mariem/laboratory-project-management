package com.labo.labo.service;

import com.labo.labo.dto.ProjetDTO;
import com.labo.labo.dto.SousFamilleDTO;
import com.labo.labo.entity.Projet;
import com.labo.labo.entity.SousFamille;
import com.labo.labo.entity.Tache;
import com.labo.labo.factory.ProjetFactory;
import com.labo.labo.factory.SousFamilleFactory;
import com.labo.labo.repository.SousFamilleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class SousFamilleService {
    private final Logger log = LoggerFactory.getLogger(SousFamilleService.class);

    @Autowired
    SousFamilleRepository sousFamilleRepository;
    public SousFamille createSousFamille(SousFamille sousFamille) {
        return sousFamilleRepository.save(sousFamille);
    }

    public List<SousFamille> getAllSousFamilles() {
        return sousFamilleRepository.findAll();
    }

    public Optional<SousFamille> getSousFamille (long id){
        return sousFamilleRepository.findById(id);
    }

    public SousFamille updateSousFamille(Long id, SousFamille sousFamilleDetails) {
        SousFamille sousFamille = sousFamilleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sous famille non trouvée avec l'id: " + id));
        sousFamille.setNom(sousFamilleDetails.getNom());
        sousFamille.setFamilles(sousFamilleDetails.getFamilles());
        return sousFamilleRepository.save(sousFamille);
    }


    public void deleteSousFamille(Long id) {
        sousFamilleRepository.deleteById(id);
    }

//    public SousFamilleService(SousFamilleRepository sousFamilleRepository) {
//        this.sousFamilleRepository = sousFamilleRepository;
//    }

//    public SousFamilleDTO save(SousFamilleDTO sousFamilleDTO) {
//        log.debug("Request to save sousFamille: {}", sousFamilleDTO);
//        SousFamille sousFamille = SousFamilleFactory.sousFamilleDTOToSousFamille(sousFamilleDTO);
//        SousFamille existingSousFamille=sousFamilleRepository.findByNom(sousFamille.getNom()); //findById return optional par défaut
//        if(existingSousFamille!=null)
//        { throw new IllegalStateException("sousFamille existe !");}
//        sousFamille = sousFamilleRepository.save(sousFamille);
//        return SousFamilleFactory.sousFamilleToSousFamilleDTO(sousFamille);
//    }
//
//    public SousFamilleDTO update(SousFamilleDTO sousFamilleDTO) {
//        log.debug("Request to update sousFamille: {}", sousFamilleDTO);
//        SousFamille sousFamille = SousFamilleFactory.sousFamilleDTOToSousFamille(sousFamilleDTO);
//        SousFamille inBase=sousFamilleRepository.findByNom(sousFamille.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("sousFamille n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//        //SousFamille inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setNom(sousFamilleDTO.getNom());
//        sousFamille = sousFamilleRepository.save(sousFamille);
//        return SousFamilleFactory.sousFamilleToSousFamilleDTO(sousFamille);
//    }
//
//    @Transactional(readOnly = true)
//    public SousFamilleDTO getSousFamilleById(Long id) { //return dto
//        log.debug("Request to get sousFamille: {}", id);
//        Optional<SousFamille> sousFamilleOpt = sousFamilleRepository.findById(id);
//        if(sousFamilleOpt.isEmpty())
//        { throw new IllegalStateException("sousFamille n'existe pas !");}
//
//        SousFamille sousFamille=sousFamilleOpt.get();
//        return SousFamilleFactory.sousFamilleToSousFamilleDTO(sousFamille);
//    }
//
//    @Transactional(readOnly = true)
//    public SousFamille getSousFamille(Long id) {   //return entité
//        log.debug("Request to get sousFamille: {}", id);
//        Optional<SousFamille> sousFamilleOpt = sousFamilleRepository.findById(id);
//        if(sousFamilleOpt.isEmpty())
//        { throw new IllegalStateException("sousFamille n'existe pas !");}
//        return sousFamilleOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<SousFamilleDTO> getAll() {
//        log.debug("Request to get All sousFamille");
//        Collection<SousFamille> result = sousFamilleRepository.findAll();
//        return SousFamilleFactory.sousFamilleToSousFamilleDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public SousFamilleDTO getSousFamilleByNom(String nom) { //return dto
//        log.debug("Request to get SousFamille: {}", nom);
//        SousFamille sousFamille = sousFamilleRepository.findByNom(nom);
//        if(sousFamille==null)
//        { throw new IllegalStateException("SousFamille n'existe pas !");}
//        return SousFamilleFactory.sousFamilleToSousFamilleDTO(sousFamille);
//    }
//    public void delete(Long id) {
//        log.debug("Request to delete sousFamille: {}", id);
//        Optional<SousFamille> sousFamilleOpt = sousFamilleRepository.findById(id);
//        if(sousFamilleOpt.isEmpty())
//        { throw new IllegalStateException("sousFamille n'existe pas !");}
//        sousFamilleRepository.deleteById(id);
//    }
//
//    /*private void checksousFamilleExists(String titre) {
//        if (sousFamilleRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("sousFamille with titre " + titre + " already exists");
//        }*/
}
