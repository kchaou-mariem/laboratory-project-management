package com.labo.labo.service;

import com.labo.labo.dto.ProjetDTO;
import com.labo.labo.entity.*;
import com.labo.labo.enumeration.Statut;
import com.labo.labo.factory.ProjetFactory;
import com.labo.labo.repository.CompteRepository;
import com.labo.labo.repository.FormuleIngredientRepository;
import com.labo.labo.repository.FormuleRepository;
import com.labo.labo.repository.ProjetRepository;
//import jakarta.transaction.Transactional;
import org.eclipse.persistence.jpa.jpql.parser.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.chrono.ChronoLocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProjetService {
    private final Logger log = LoggerFactory.getLogger(ProjetService.class);
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    FormuleRepository formuleRepository;
    @Autowired
    CompteRepository compteRepository;
    @Autowired
    FormuleIngredientRepository formuleIngredientRepository;
    public Projet createProjet(Projet projet) {
        // projet.setDateCreationProjet(LocalDate.now());
        if (projet.getDateFin().isBefore(projet.getDateDebut())){
            throw new IllegalArgumentException("date fin doit etre psoterieure à la date début: ");
        }

        if (projetRepository.findByNom(projet.getNom()).isPresent()) {
            throw new IllegalArgumentException("Nom déjà utilisé");
        }

        return projetRepository.save(projet);
    }

    public List<Projet> getAllProjets() {
        return projetRepository.findAll();
    }

    public Optional<Projet> getProjet(long id) {
        return projetRepository.findById(id);
    }

    public Projet updateStatutProjet(Long id, Statut newStatut) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("projet non trouvée avec l'id: " + id));
        projet.setStatut(newStatut);
        return projetRepository.save(projet);
    }

    public Projet updateProjet(Long id, Projet projetDetails) {
        Projet projet = projetRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("projet non trouvée avec l'id: " + id));
        projet.setNom(projetDetails.getNom());
        projet.setComment(projetDetails.getComment());
        projet.setDateDebut(projetDetails.getDateDebut());
        projet.setDateFin(projetDetails.getDateFin());
        projet.setDescription(projetDetails.getDescription());
        projet.setParQUI(projetDetails.getParQUI());
        projet.setStatut(projetDetails.getStatut());
        projet.setComptes(projetDetails.getComptes());
        projet.setChefProjetId(projetDetails.getChefProjetId());
       // projet.setRealisation(projetDetails.getRealisation());
        if (projet.getDateFin().isBefore(projet.getDateDebut())){
            throw new IllegalArgumentException("date fin doit etre psoterieure à la date début: ");
        }
        return projetRepository.save(projet);
    }

    //    public void CalculerTempsRealiseTotalAndPourcentage(Projet projet){
//        projet.setTempsRealiseTotal(projet.getTempsRealiseTotal()+ projet.getTempsRealise());
//        projet.setPourcentageTpsRealise((projet.getTempsRealiseTotal()*100)/ projet.getTempsEstime());
//    }
    public void deleteProjet(Long id) {
        Optional<Projet>projet=projetRepository.findById(id);
        if (projet.isPresent()) {
            List<Formule> formules = projet.get().getFormules();
            if (formules != null) {
                for (Formule f : formules) {
                    List<FormuleIngredient> formuleIngredients = f.getFormuleIngredients();
                    for (FormuleIngredient fi : formuleIngredients) {
                        formuleIngredientRepository.delete(fi);
                    }

                    formuleRepository.delete(f);
                }
            }


            projetRepository.deleteById(id);
        }
    }

    public List<Formule> getFormulesByProjet(Long id) {
        List<Formule> formules = formuleRepository.findFormulesByProjetId(id);
        return formules;
    }


    public Projet updateStatusVenir(Long id){
        Projet projet = projetRepository.findById(id).get();
        List<Formule> formules = projet.getFormules();
        int s = 0;
        for(Formule f: projet.getFormules()){
            if(f.getStatut().equals(Statut.A_VENIR)){
                s++;
            }
        }
        if (s==projet.getFormules().size()){
            projet.setStatut(Statut.A_VENIR);
        }
        return projetRepository.save(projet);
    }

    public Projet updateStatus(Long id) {
        Projet projet = projetRepository.findById(id).get();
            List<Formule> formules = projet.getFormules();
            int s = 0;
            boolean isTermine = false;
            boolean isC=false;

            for (Formule f: formules){
                if (f.getStatut().equals(Statut.EN_COURS)){
                    isC=true;
                    break;
                }
                else if  (f.getStatut().equals(Statut.TERMINE) || f.getStatut().equals(Statut.REJETE)) {
                    s++;
                    if (f.getStatut().equals(Statut.TERMINE)) {
                        isTermine = true;
                    }
                }
            }

            if(isC){
            projet.setStatut(Statut.EN_COURS);
            }
            else  {
                if(s==formules.size()) {
                    if (isTermine) {
                        projet.setStatut(Statut.TERMINE);
                    } else {
                        projet.setStatut(Statut.EN_ATTENTE);
                    }
                }
            }

            projet = projetRepository.save(projet);
            return projet;
    }

public List<Projet>findProjetByNomsStartingWith(String search){
        return projetRepository.findByNomStartingWithIgnoreCase(search);
}
//    public Projet updateStatus(Long id) {
//        Projet projet = projetRepository.findById(id).get();
//        List<Formule> formules = projet.getFormules();
//        int s = 0;
//        boolean isTermine = false;
//        boolean isC=false;
//
//        for (Formule f: formules){
//            if (f.getStatut().equals(Statut.EN_COURS)){
//                isC=true;
//                break;
//            }
//            else if  (f.getStatut().equals(Statut.TERMINE) || f.getStatut().equals(Statut.REJETE)) {
//                if (f.getStatut().equals(Statut.TERMINE)) {
//                    isTermine = true;
//                }
//            }
//        }
//
//        if(isC){
//            projet.setStatut(Statut.EN_COURS);
//        }
//        else  {
//                if (isTermine) {
//                    projet.setStatut(Statut.TERMINE);
//                } else {
//                    projet.setStatut(Statut.EN_ATTENTE);
//                }
//        }
//
//        projet = projetRepository.save(projet);
//        return projet;
//    }
}
