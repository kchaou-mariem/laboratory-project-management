package com.labo.labo.service;

import com.labo.labo.dto.DocumentDTO;
import com.labo.labo.dto.PlageHoraireEquipementTacheDTO;
import com.labo.labo.entity.ClePlageHoraireEquipementTache;
import com.labo.labo.entity.Document;
import com.labo.labo.entity.PlageHoraireEquipementTache;
import com.labo.labo.factory.DocumentFactory;
import com.labo.labo.factory.PlageHoraireEquipementTacheFactory;
import com.labo.labo.repository.PlageHoraireEquipementTacheRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class PlageHoraireEquipementTacheService {

    private final Logger log = LoggerFactory.getLogger(PlageHoraireEquipementTacheService.class);

    private final PlageHoraireEquipementTacheRepository plageHoraireEquipementTacheRepository;

    public PlageHoraireEquipementTacheService(PlageHoraireEquipementTacheRepository plageHoraireEquipementTacheRepository) {
        this.plageHoraireEquipementTacheRepository = plageHoraireEquipementTacheRepository;
    }

    public PlageHoraireEquipementTacheDTO save(PlageHoraireEquipementTacheDTO plageHoraireEquipementTacheDTO) {
        log.debug("Request to save plageHoraireEquipement: {}", plageHoraireEquipementTacheDTO);
        PlageHoraireEquipementTache plageHoraireEquipementTache = PlageHoraireEquipementTacheFactory.plageHoraireEquipementTacheDTOToPlageHoraireEquipementTache(plageHoraireEquipementTacheDTO);
        Optional<PlageHoraireEquipementTache> existingPlageHoraireEquipementTache=plageHoraireEquipementTacheRepository.findById(plageHoraireEquipementTache.getId()); //findById return optional par défaut
        if(existingPlageHoraireEquipementTache.isPresent())
        { throw new IllegalStateException("plageHoraireEquipementTache existe !");}
        plageHoraireEquipementTache = plageHoraireEquipementTacheRepository.save(plageHoraireEquipementTache);
        return PlageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToDTO(plageHoraireEquipementTache);
    }

    public PlageHoraireEquipementTacheDTO update(PlageHoraireEquipementTacheDTO plageHoraireEquipementTacheDTO) {
        log.debug("Request to update plageHoraireEquipementTache: {}", plageHoraireEquipementTacheDTO);
        PlageHoraireEquipementTache plageHoraireEquipementTache = PlageHoraireEquipementTacheFactory.plageHoraireEquipementTacheDTOToPlageHoraireEquipementTache(plageHoraireEquipementTacheDTO);
        Optional<PlageHoraireEquipementTache> inBaseOpt = plageHoraireEquipementTacheRepository.findById(plageHoraireEquipementTache.getId());
        if(inBaseOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
        PlageHoraireEquipementTache inBase = inBaseOpt.get(); //transformer optional en entité
        inBase.setHoraireD(plageHoraireEquipementTacheDTO.getHoraireD());
        inBase.setHoraireF(plageHoraireEquipementTacheDTO.getHoraireF());
        plageHoraireEquipementTache = plageHoraireEquipementTacheRepository.save(plageHoraireEquipementTache);
        return PlageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToDTO(plageHoraireEquipementTache);
    }

    @Transactional(readOnly = true)
    public PlageHoraireEquipementTacheDTO getPlageHoraireEquipementTacheById(ClePlageHoraireEquipementTache id) { //return dto
        log.debug("Request to get document: {}", id);
        Optional<PlageHoraireEquipementTache> plageHoraireEquipementTacheOpt = plageHoraireEquipementTacheRepository.findById(id);
        if(plageHoraireEquipementTacheOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        PlageHoraireEquipementTache plageHoraireEquipementTache=plageHoraireEquipementTacheOpt.get();
        return PlageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToDTO(plageHoraireEquipementTache);
    }

    @Transactional(readOnly = true)
    public PlageHoraireEquipementTache getdPlageHoraireEquipementTache(ClePlageHoraireEquipementTache id) {   //return entité
        log.debug("Request to get plageHoraireEquipementTache: {}", id);
        Optional<PlageHoraireEquipementTache> plageHoraireEquipementTacheOpt = plageHoraireEquipementTacheRepository.findById(id);
        if(plageHoraireEquipementTacheOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        return plageHoraireEquipementTacheOpt.get();
    }

    @Transactional(readOnly = true)
    public Collection<PlageHoraireEquipementTacheDTO> getAll() {
        log.debug("Request to get All plageHoraireEquipementTache");
        Collection<PlageHoraireEquipementTache> result = plageHoraireEquipementTacheRepository.findAll();
        return PlageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToDTOs(result);
    }

    public void delete(ClePlageHoraireEquipementTache id) {
        log.debug("Request to delete document: {}", id);
        Optional<PlageHoraireEquipementTache> documentOpt = plageHoraireEquipementTacheRepository.findById(id);
        if(documentOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        plageHoraireEquipementTacheRepository.deleteById(id);
    }

    /*private void checkdocumentExists(String titre) {
        if (documentRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("document with titre " + titre + " already exists");
        }
*/
}
