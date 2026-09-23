package com.labo.labo.service;

import com.labo.labo.dto.AnnonceDTO;
import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Tache;
import com.labo.labo.factory.AnnonceFactory;
import com.labo.labo.repository.AnnonceRepository;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.eclipse.persistence.annotations.ReadOnly;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.hibernate.query.sqm.tree.SqmNode.log;

@Service
public class AnnonceService {

    @Autowired
     AnnonceRepository annonceRepository;

    public Annonce createAnnonce(Annonce annonce) {
        return annonceRepository.save(annonce);
    }

    public List<Annonce> getAllAnnonces() {
        return annonceRepository.findAll();
    }

    public Optional<Annonce> getAnnonce (long id){
        return annonceRepository.findById(id);
    }

    public Annonce updateAnnonce(Long id, Annonce annonceDetails) {
        Annonce annonce = annonceRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Annonce non trouvée avec l'id: " + id));
        annonce.setTitre(annonceDetails.getTitre());
        annonce.setDescription(annonceDetails.getDescription());

        return annonceRepository.save(annonce);
    }

    public void deleteAnnonce(Long id) {
        annonceRepository.deleteById(id);
    }


//    public AnnonceService(AnnonceRepository annonceRepository) {
//        this.annonceRepository = annonceRepository;
//    }
//
//    public AnnonceDTO save(AnnonceDTO annonceDTO) {
//       log.debug("Request to save annonce: {}", annonceDTO);
//        Annonce annonce = AnnonceFactory.annonceDTOToAnnonce(annonceDTO);
//        Annonce existingAnnonce=annonceRepository.findByTitre(annonce.getTitre()); //findById return optional par défaut
//        if(existingAnnonce!=null)
//        { throw new IllegalStateException("annonce existe !");}
//        annonce = annonceRepository.save(annonce);
//        return AnnonceFactory.annonceToAnnonceDTO(annonce);
//    }
//
//    public AnnonceDTO update(AnnonceDTO annonceDTO) {
//        log.debug("Request to update annonce: {}", annonceDTO);
//        Annonce annonce = AnnonceFactory.annonceDTOToAnnonce(annonceDTO);
//        Optional<Annonce> inBaseOpt = annonceRepository.findById(annonce.getIdAnnonce());
//        if(inBaseOpt.isEmpty())
//        { throw new IllegalStateException("annonce n'existe pas !");}
//       //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//        Annonce inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setTitre(annonceDTO.getTitre());
//        inBase.setDescription(annonceDTO.getDescription());
//        annonce = annonceRepository.save(annonce);
//        return AnnonceFactory.annonceToAnnonceDTO(annonce);
//    }
//
//    @Transactional(readOnly = true)
//    public AnnonceDTO getAnnonceById(Long id) { //return dto
//        log.debug("Request to get annonce: {}", id);
//        Optional<Annonce> annonceOpt = annonceRepository.findById(id);
//        if(annonceOpt.isEmpty())
//        { throw new IllegalStateException("annonce n'existe pas !");}
//        Annonce annonce=annonceOpt.get();
//        return AnnonceFactory.annonceToAnnonceDTO(annonce);
//    }
//
//    @Transactional(readOnly = true)
//    public Annonce getAnnonce(Long id) {   //return entité
//        log.debug("Request to get Annonce: {}", id);
//        Optional<Annonce> annonceOpt = annonceRepository.findById(id);
//        if(annonceOpt.isEmpty())
//        { throw new IllegalStateException("annonce n'existe pas !");}
//        return annonceOpt.get();
//    }
//    @Transactional(readOnly = true)
//    public AnnonceDTO getAnnonceByTitre(String titre) { //return dto
//        log.debug("Request to get annonce: {}", titre);
//        Annonce annonce = annonceRepository.findByTitre(titre);
//        if(annonce==null)
//        { throw new IllegalStateException("annonce n'existe pas !");}
//        return AnnonceFactory.annonceToAnnonceDTO(annonce);
//    }
//    @Transactional(readOnly = true)
//    public Collection<AnnonceDTO> getAll() {
//        log.debug("Request to get All annonce");
//        Collection<Annonce> result = annonceRepository.findAll();
//        return AnnonceFactory.annonceToAnnonceDTOs(result);
//    }
//
//    public void delete(Long id) {
//        log.debug("Request to delete annonce: {}", id);
//        Optional<Annonce> annonceOpt = annonceRepository.findById(id);
//        if(annonceOpt.isEmpty())
//        { throw new IllegalStateException("annonce n'existe pas !");}
//        annonceRepository.deleteById(id);
//    }

    /*private void checkAnnonceExists(String titre) {
        if (annonceRepository.findByTitre(titre) != null) {
            throw new IllegalStateException("Annonce with titre " + titre + " already exists");
        }*/
}