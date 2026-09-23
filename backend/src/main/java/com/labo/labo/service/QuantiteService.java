package com.labo.labo.service;

import com.labo.labo.dto.ProjetDTO;
import com.labo.labo.dto.QuantiteDTO;
import com.labo.labo.entity.CleQuantite;
import com.labo.labo.entity.Projet;
import com.labo.labo.entity.Quantite;
import com.labo.labo.factory.ProjetFactory;
import com.labo.labo.factory.QuantiteFactory;
import com.labo.labo.repository.ProjetRepository;
import com.labo.labo.repository.QuantiteRepository;
//import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.Collection;
import java.util.Optional;

@Service
public class QuantiteService {
    private final Logger log = LoggerFactory.getLogger(QuantiteService.class);

    private final QuantiteRepository quantiteRepository;

    public QuantiteService(QuantiteRepository quantiteRepository) {
        this.quantiteRepository = quantiteRepository;
    }

    public QuantiteDTO save(QuantiteDTO quantiteDTO) {
        log.debug("Request to save quantite: {}", quantiteDTO);
        Quantite quantite = QuantiteFactory.quantiteDTOToQuantite(quantiteDTO);
        Optional<Quantite> existingQuantite=quantiteRepository.findById(quantite.getIdQuantite()); //findById return optional par défaut
        if(existingQuantite.isPresent())
        { throw new IllegalStateException("quantite existe !");}
        quantite = quantiteRepository.save(quantite);
        return QuantiteFactory.quantiteToQuantiteDTO(quantite);
    }

    public QuantiteDTO update(QuantiteDTO quantiteDTO) {
        log.debug("Request to update quantite: {}", quantiteDTO);
        Quantite quantite = QuantiteFactory.quantiteDTOToQuantite(quantiteDTO);
        Optional<Quantite> inBaseOpt = quantiteRepository.findById(quantite.getIdQuantite());
        if(inBaseOpt.isEmpty())
        { throw new IllegalStateException("quantite n'existe pas !");}
        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
        Quantite inBase = inBaseOpt.get(); //transformer optional en entité
        inBase.setQuantite(quantiteDTO.getQuantite());
        quantite = quantiteRepository.save(quantite);
        return QuantiteFactory.quantiteToQuantiteDTO(quantite);
    }

    @Transactional(readOnly = true)
    public QuantiteDTO getQuantiteById(CleQuantite id) { //return dto
        log.debug("Request to get quantite: {}", id);
        Optional<Quantite> quantiteOpt = quantiteRepository.findById(id);
        if(quantiteOpt.isEmpty())
        { throw new IllegalStateException("quantite n'existe pas !");}
        Quantite quantite=quantiteOpt.get();
        return QuantiteFactory.quantiteToQuantiteDTO(quantite);
    }

    @Transactional(readOnly = true)
    public Quantite getquantite(CleQuantite id) {   //return entité
        log.debug("Request to get quantite: {}", id);
        Optional<Quantite> quantiteOpt = quantiteRepository.findById(id);
        if(quantiteOpt.isEmpty())
        { throw new IllegalStateException("quantite n'existe pas !");}
        return quantiteOpt.get();
    }

    @Transactional(readOnly = true)
    public Collection<QuantiteDTO> getAll() {
        log.debug("Request to get All quantite");
        Collection<Quantite> result = quantiteRepository.findAll();
        return QuantiteFactory.quantiteToQuantiteDTOs(result);
    }

    public void delete(CleQuantite id) {
        log.debug("Request to delete quantite: {}", id);
        Optional<Quantite> quantiteOpt = quantiteRepository.findById(id);
        if(quantiteOpt.isEmpty())
        { throw new IllegalStateException("quantite n'existe pas !");}
        quantiteRepository.deleteById(id);
    }

    /*private void checkquantiteExists(String titre) {
        if (quantiteRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("quantite with titre " + titre + " already exists");
        }*/
}
