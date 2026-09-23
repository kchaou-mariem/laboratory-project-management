/*package com.labo.labo.service;
import com.labo.labo.dto.PlageHoraireEquipementTacheDTO;
import com.labo.labo.entity.PlageHoraireEquipementTache;
import com.labo.labo.factory.PlageHoraireEquipementTacheFactory;
import com.labo.labo.repository.PlageHoraireEquipementTacheRepository;
//import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Collection;
import java.util.Optional;

import org.springframework.transaction.annotation.Transactional;

@Service
public class plageHoraireEquipementTacheService {
    private final Logger log = LoggerFactory.getLogger(plageHoraireEquipementTacheService.class);

    private final PlageHoraireEquipementTacheRepository plageHoraireEquipementTacheRepository;

    public plageHoraireEquipementTacheService(PlageHoraireEquipementTacheRepository plageHoraireEquipementTacheRepository) {
        this.plageHoraireEquipementTacheRepository = plageHoraireEquipementTacheRepository;
    }

    public PlageHoraireEquipementTacheDTO save(PlageHoraireEquipementTacheDTO plageHoraireEquipementTacheDTO) {
        log.debug("Request to save plageHoraireEquipementTache: {}", plageHoraireEquipementTacheDTO);
        PlageHoraireEquipementTache plageHoraireEquipementTache = PlageHoraireEquipementTacheFactory.plageHoraireEquipementTacheDTOToPlageHoraireEquipementTache(plageHoraireEquipementTacheDTO);
        Optional<PlageHoraireEquipementTache> existingPlageHoraireEquipementTache=plageHoraireEquipementTacheRepository.findById(plageHoraireEquipementTache.getIdPlageHoraire()); //findById return optional par défaut
        if(existingplageHoraireEquipementTache.isPresent())
        { throw new IllegalStateException("plageHoraireEquipementTache existe !");}
        plageHoraireEquipementTache = plageHoraireEquipementTacheRepository.save(plageHoraireEquipementTache);
        return plageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToplageHoraireEquipementTacheDTO(plageHoraireEquipementTache);
    }

    public plageHoraireEquipementTacheDTO update(plageHoraireEquipementTacheDTO plageHoraireEquipementTacheDTO) {
        log.debug("Request to update plageHoraireEquipementTache: {}", plageHoraireEquipementTacheDTO);
        plageHoraireEquipementTache plageHoraireEquipementTache = plageHoraireEquipementTacheFactory.plageHoraireEquipementTacheDTOToplageHoraireEquipementTache(plageHoraireEquipementTacheDTO);
        Optional<plageHoraireEquipementTache> inBaseOpt = plageHoraireEquipementTacheRepository.findById(plageHoraireEquipementTache.getIdplageHoraireEquipementTache());
        if(inBaseOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
        plageHoraireEquipementTache inBase = inBaseOpt.get(); //transformer optional en entité
        inBase.setDateDebut(plageHoraireEquipementTacheDTO.getDateDebut());
        inBase.setDateFin(plageHoraireEquipementTacheDTO.getDateFin());
        inBase.setNom(plageHoraireEquipementTacheDTO.getNom());
        inBase.setStatut(plageHoraireEquipementTacheDTO.getStatut());
        inBase.setTempsEstime(plageHoraireEquipementTacheDTO.getTempsEstime());
        inBase.setTempsRealise(plageHoraireEquipementTacheDTO.getTempsRealise());
        plageHoraireEquipementTache = plageHoraireEquipementTacheRepository.save(plageHoraireEquipementTache);
        return plageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToplageHoraireEquipementTacheDTO(plageHoraireEquipementTache);
    }

    @Transactional(readOnly = true)
    public plageHoraireEquipementTacheDTO getplageHoraireEquipementTacheById(Long id) { //return dto
        log.debug("Request to get plageHoraireEquipementTache: {}", id);
        Optional<plageHoraireEquipementTache> plageHoraireEquipementTacheOpt = plageHoraireEquipementTacheRepository.findById(id);
        if(plageHoraireEquipementTacheOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        plageHoraireEquipementTache plageHoraireEquipementTache=plageHoraireEquipementTacheOpt.get();
        return plageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToplageHoraireEquipementTacheDTO(plageHoraireEquipementTache);
    }

    @Transactional(readOnly = true)
    public plageHoraireEquipementTache getplageHoraireEquipementTache(Long id) {   //return entité
        log.debug("Request to get plageHoraireEquipementTache: {}", id);
        Optional<plageHoraireEquipementTache> plageHoraireEquipementTacheOpt = plageHoraireEquipementTacheRepository.findById(id);
        if(plageHoraireEquipementTacheOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        return plageHoraireEquipementTacheOpt.get();
    }

    @Transactional(readOnly = true)
    public Collection<plageHoraireEquipementTacheDTO> getAll() {
        log.debug("Request to get All plageHoraireEquipementTache");
        Collection<plageHoraireEquipementTache> result = plageHoraireEquipementTacheRepository.findAll();
        return plageHoraireEquipementTacheFactory.plageHoraireEquipementTacheToplageHoraireEquipementTacheDTOs(result);
    }

    public void delete(Long id) {
        log.debug("Request to delete plageHoraireEquipementTache: {}", id);
        Optional<plageHoraireEquipementTache> plageHoraireEquipementTacheOpt = plageHoraireEquipementTacheRepository.findById(id);
        if(plageHoraireEquipementTacheOpt.isEmpty())
        { throw new IllegalStateException("plageHoraireEquipementTache n'existe pas !");}
        plageHoraireEquipementTacheRepository.deleteById(id);
    }

    /*private void checkplageHoraireEquipementTacheExists(String titre) {
        if (plageHoraireEquipementTacheRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("plageHoraireEquipementTache with titre " + titre + " already exists");
        }

}
*/