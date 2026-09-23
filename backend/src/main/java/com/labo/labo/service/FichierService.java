package com.labo.labo.service;

import com.labo.labo.dto.FamilleDTO;
import com.labo.labo.dto.FichierDTO;
import com.labo.labo.entity.Annonce;
import com.labo.labo.entity.Famille;
import com.labo.labo.entity.Fichier;
import com.labo.labo.factory.FamilleFactory;
import com.labo.labo.factory.FichierFactory;
import com.labo.labo.repository.FichierRepository;
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
public class FichierService {

    @Autowired
    FichierRepository fichierRepository;
    public  Fichier createFichier( Fichier fichier) {
        return fichierRepository.save(fichier);
    }

    public List<Fichier> getAllFichiers() {
        return fichierRepository.findAll();
    }

    public Optional<Fichier> getFichier (long id){
        return fichierRepository.findById(id);
    }

    public Fichier updateFichier(Long id, Fichier fichierDetails) {
        Fichier fichier = fichierRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Fichier non trouvée avec l'id: " + id));
        fichier.setNom(fichierDetails.getNom());
        fichier.setChemin(fichierDetails.getChemin());
        return fichierRepository.save(fichier);
    }

    public void deleteFichier(Long id) {
        fichierRepository.deleteById(id);
    }

//    public FichierDTO save(FichierDTO fichierDTO) {
//        log.debug("Request to save fichier: {}", fichierDTO);
//        Fichier fichier = FichierFactory.fichierDTOToFichier(fichierDTO);
//        Fichier existingFichier=fichierRepository.findByNom(fichier.getNom()); //findById return optional par défaut
//        if(existingFichier!=null)
//        { throw new IllegalStateException("fichier existe !");}
//        fichier = fichierRepository.save(fichier);
//        return FichierFactory.fichierToFichierDTO(fichier);
//    }
//
//    public FichierDTO update(FichierDTO fichierDTO) {
//        log.debug("Request to update fichier: {}", fichierDTO);
//        Fichier fichier = FichierFactory.fichierDTOToFichier(fichierDTO);
//        Fichier inBase=fichierRepository.findByNom(fichier.getNom());
//        if(inBase==null)
//        { throw new IllegalStateException("fichier n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//        //Fichier inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setNom(fichierDTO.getNom());
//        inBase.setChemin(fichierDTO.getChemin());
//        fichier = fichierRepository.save(fichier);
//        return FichierFactory.fichierToFichierDTO(fichier);
//    }
//
//    @Transactional(readOnly = true)
//    public FichierDTO getFichierById(Long id) { //return dto
//        log.debug("Request to get fichier: {}", id);
//        Optional<Fichier> fichierOpt = fichierRepository.findById(id);
//        if(fichierOpt.isEmpty())
//        { throw new IllegalStateException("fichier n'existe pas !");}
//        Fichier fichier=fichierOpt.get();
//        return FichierFactory.fichierToFichierDTO(fichier);
//    }
//
//    @Transactional(readOnly = true)
//    public Fichier getFichier(Long id) {   //return entité
//        log.debug("Request to get fichier: {}", id);
//        Optional<Fichier> fichierOpt = fichierRepository.findById(id);
//        if(fichierOpt.isEmpty())
//        { throw new IllegalStateException("fichier n'existe pas !");}
//        return fichierOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<FichierDTO> getAll() {
//        log.debug("Request to get All fichier");
//        Collection<Fichier> result = fichierRepository.findAll();
//        return FichierFactory.fichierToFichierDTOs(result);
//    }
//    @Transactional(readOnly = true)
//    public FichierDTO getFichierByNom(String nom) { //return dto
//        log.debug("Request to get Fichier: {}", nom);
//        Fichier fichier = fichierRepository.findByNom(nom);
//        if(fichier==null)
//        { throw new IllegalStateException("Fichier n'existe pas !");}
//        return FichierFactory.fichierToFichierDTO(fichier);
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<FichierDTO> getFichierByNomAnnonce(String annonce) { //return dto
//        log.debug("Request to get Fichier: {}", annonce);
//        Collection<Fichier> fichiers = fichierRepository.findByAnnonces_Titre(annonce);
//        if(fichiers==null)
//        { throw new IllegalStateException("Fichier n'existe pas !");}
//        return FichierFactory.fichierToFichierDTOs(fichiers);
//    }
//    @Transactional(readOnly = true)
//    public Collection<FichierDTO> getFichierByNomProjet(String projet) { //return dto
//        log.debug("Request to get Fichier: {}", projet);
//        Collection<Fichier> fichiers = fichierRepository.findByProjet_Nom(projet);
//        if(fichiers==null)
//        { throw new IllegalStateException("Fichier n'existe pas !");}
//        return FichierFactory.fichierToFichierDTOs(fichiers);
//    }
//
//
//
//    public void delete(Long id) {
//        log.debug("Request to delete fichier: {}", id);
//        Optional<Fichier> fichierOpt = fichierRepository.findById(id);
//        if(fichierOpt.isEmpty())
//        { throw new IllegalStateException("fichier n'existe pas !");}
//        fichierRepository.deleteById(id);
//    }
//
//    /*private void checkfichierExists(String titre) {
//        if (fichierRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("fichier with titre " + titre + " already exists");
//        }*/

}
