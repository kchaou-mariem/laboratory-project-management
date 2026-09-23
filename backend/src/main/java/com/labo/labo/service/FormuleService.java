package com.labo.labo.service;

import com.labo.labo.entity.*;
import com.labo.labo.enumeration.Statut;
import com.labo.labo.repository.*;
//import jakarta.transaction.Transactional;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class FormuleService {

    @Autowired
    FormuleRepository formuleRepository;
    @Autowired
    FormuleIngredientRepository formuleIngredientRepository;
    @Autowired
    IngredientRepository ingredientRepository;
    @Autowired
    ProjetRepository projetRepository;
    @Autowired
    PhaseRepository phaseRepository;
    @Autowired
    ProjetService projetService;
    @Transactional
    public Formule createFormule(Formule formule) {
        formule.setDateCreation(LocalDate.now());
        Projet projet = projetRepository.findById(formule.getProjet().getId()).orElse(null);
        if (projet != null) {
            if (projet.getFormules().isEmpty()) {
                formule.setFormuleIndex(1);
            } else {
                int maxIndex = projet.getFormules().stream()
                        .mapToInt(Formule::getFormuleIndex)
                        .max()
                        .orElse(0); // Trouver l'index le plus élevé
                formule.setFormuleIndex(maxIndex + 1);
            }
        }
        formule = formuleRepository.save(formule); //pour creer id
        if (formule.getFormuleIngredients() != null && formule.getFormuleIngredients().size() > 0) {
            for (FormuleIngredient fi : formule.getFormuleIngredients()) {
                fi.setFormule(formule);
                formuleIngredientRepository.save(fi);
            }
        }

        return formuleRepository.save(formule);
    }

    @Transactional
    public Formule reviserFormule(Long id){
        Formule formule = formuleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formule non trouvée avec l'id: " + id));
        formule.setStatut(Statut.A_VENIR);
        phaseRepository.deleteAll(formule.getPhases());
        formuleRepository.save(formule);
        projetService.updateStatusVenir(formule.getProjet().getId());
        return formule;
    }
    public List<Formule> getFormulesByProjet(Long id) {
        List<Formule> formules = formuleRepository.findFormulesByProjetId(id);
        return formules;
    }

    public List<Formule> getAllFormules() {
        return formuleRepository.findAll();
    }

    public Formule getFormule(long id) {

        Formule formule = formuleRepository.findById(id).get();
        Phase fab = formule.getPhases().stream().filter(el -> el.getFromFabrication() == true).findFirst().orElse(null);
        formule.setPhaseFabrication(fab);
        Phase test = formule.getPhases().stream().filter(el -> el.getFromTest() == true).findFirst().orElse(null);
        formule.setPhaseTest(test);
        return formule;
    }

    public Formule updateFormule(Long id, Formule formuleDetails) {
        Formule formule = formuleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formule non trouvée avec l'id: " + id));
        formule.setStatut(formuleDetails.getStatut());
        formule.setMarqueInspireDe(formuleDetails.getMarqueInspireDe());
        formule.setProduitInspireDe(formuleDetails.getProduitInspireDe());
        formule.setPhases(formuleDetails.getPhases());
        formule.setPhaseFabrication(formuleDetails.getPhaseFabrication());
        formule.setPhaseTest(formuleDetails.getPhaseTest());
        //formule.setFormuleIngredients(formuleDetails.getFormuleIngredients());
        List<FormuleIngredient> newFormuleIngredients = new ArrayList<>();

        for (FormuleIngredient updatedFi : formuleDetails.getFormuleIngredients()) {
            if (updatedFi == null || updatedFi.getIngredient() == null) {
                continue;
            } //si null alors on saute car on peut pas étudier sa quantité etc..

            FormuleIngredient formuleIngredient = formule.getFormuleIngredients().stream() //reel non updated
                    .filter(fi -> fi.getIngredient().getId().equals(updatedFi.getIngredient().getId()))
                    .findFirst()
                    .orElseGet(() -> {
                        Ingredient ingredient = ingredientRepository.findById(updatedFi.getIngredient().getId())
                                .orElseThrow(() -> new IllegalArgumentException("ingrédient non trouvée avec l'id: " + updatedFi.getIngredient().getId()));
                        FormuleIngredient newFi = new FormuleIngredient();
                        newFi.setIngredient(ingredient);
                        newFi.setFormule(formule);
                        return newFi;
                    });

            formuleIngredient.setQuantite(updatedFi.getQuantite());
            newFormuleIngredients.add(formuleIngredient);
        }

        formule.setFormuleIngredients(newFormuleIngredients);
        formuleIngredientRepository.saveAll(newFormuleIngredients);
        return formuleRepository.save(formule);

    }

    public Formule validerFormule(Long id) {
        Formule formule = formuleRepository.findById(id).get();
        formule.setStatut(Statut.VALIDE);
        formuleRepository.save(formule);
        Projet projet = formule.getProjet();
        projet.setStatut(Statut.VALIDE);
        projetRepository.save(projet);
        return formule;
    }

    public void deleteFormule(Long id) {
        Optional<Formule> formule=formuleRepository.findById(id);
        if(formule.isPresent()) {
            List<FormuleIngredient> formuleIngredients = formule.get().getFormuleIngredients();
            if (formuleIngredients != null) {
               for (FormuleIngredient fi: formuleIngredients ){
                   formuleIngredientRepository.delete(fi);
               }
            }
        }

        formuleRepository.deleteById(id);
    }


}

//    double newQuantite = updatedFi.getQuantite() != null ? updatedFi.getQuantite() : 0.0;
////            formuleIngredient.setQuantite(updatedFi.getQuantite() != null ? updatedFi.getQuantite() : 0.0); //update la quantite lors de l'update d'un ingrediant existant
////            newFormuleIngredients.add(formuleIngredient);
////
////            Ingredient ingredient = formuleIngredient.getIngredient();
////            double newIngredientQuantite = ingredient.getQuantite() - newQuantite;
////            ingredient.setQuantite(newIngredientQuantite);
////            ingredientRepository.save(ingredient);
////        }