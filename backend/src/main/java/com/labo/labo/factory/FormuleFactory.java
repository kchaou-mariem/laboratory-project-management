package com.labo.labo.factory;

import com.labo.labo.dto.FormuleDTO;
import com.labo.labo.entity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class FormuleFactory {
//    public static FormuleDTO formuleToFormuleDTO(Formule formule) {
//        FormuleDTO formuleDTO = new FormuleDTO();
//        formuleDTO.setDateCreation(formule.getDateCreation());
//        formuleDTO.setStatut(formule.getStatut());
//        formuleDTO.setMarqueInspireDe(formule.getMarqueInspireDe());
//        List<String> nomsPhases = new ArrayList<>();
//        for(DetailsPhase detailsPhase : formule.getDetailsPhases()) {
//            nomsPhases.add(detailsPhase.getNom());
//        }
//        formuleDTO.setPhases(nomsPhases);
//
//        formuleDTO.setProjet(formule.getProjet().getNom());
//
//        List<String> nomsIngredients = new ArrayList<>();
//        for(Ingredient ingredient : formule.getIngredients()) {
//            nomsIngredients.add(ingredient.getNomCommercial());
//        }
//        formuleDTO.setIngredients(nomsIngredients);
//
//        return formuleDTO;
//    }
//
//    public static Formule formuleDTOToFormule(FormuleDTO formuleDTO) {
//        Formule formule = new Formule();
//        formule.setDateCreation(formuleDTO.getDateCreation());
//        formule.setStatut(formuleDTO.getStatut());
//        formule.setMarqueInspireDe(formuleDTO.getMarqueInspireDe());
//        formule.getProjet().setNom(formuleDTO.getProjet());
//        List<Ingredient> ingredients = new ArrayList<>();
//        for(String nomIngredient : formuleDTO.getIngredients()) {
//            Ingredient ingredient = new Ingredient();
//            ingredient.setNomCommercial(nomIngredient);
//            ingredients.add(ingredient);
//        }
//        formule.setIngredients(ingredients);
//
//        List<DetailsPhase> detailsPhases = new ArrayList<>();
//        for(String nomPhase : formuleDTO.getPhases()) {
//            DetailsPhase detailsPhase = new DetailsPhase();
//            detailsPhase.setNom(nomPhase);
//            detailsPhases.add(detailsPhase);
//        }
//        formule.setIngredients(ingredients);
//
//        return formule;
//    }

//    public static Collection<FormuleDTO> formuleToFormuleDTOs(Collection<Formule> formules) {
//        List<FormuleDTO> formulesDTO = new ArrayList<>();
//        formules.forEach(x -> formulesDTO.add(formuleToFormuleDTO(x)));
//        return formulesDTO;
//    }
}
