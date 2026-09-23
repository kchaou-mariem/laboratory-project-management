package com.labo.labo.service;

import com.labo.labo.dto.GammeDTO;
import com.labo.labo.dto.IngredientDTO;
import com.labo.labo.entity.Formule;
import com.labo.labo.entity.Gamme;
import com.labo.labo.entity.Ingredient;
import com.labo.labo.factory.GammeFactory;
import com.labo.labo.factory.IngredientFactory;
import com.labo.labo.repository.IngredientRepository;
//import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class IngredientService {
    private final Logger log = LoggerFactory.getLogger(IngredientService.class);

    @Autowired
   IngredientRepository ingredientRepository;

    public Ingredient createIngredient(Ingredient ingredient) {
        if (ingredientRepository.findByNomCommercial(ingredient.getNomCommercial()).isPresent()) {
            throw new IllegalArgumentException("Nom Commercial déjà utilisé");
        }
        if (ingredientRepository.findByCasN(ingredient.getCasN()).isPresent()) {
            throw new IllegalArgumentException("Cas°N déjà utilisé");
        }
        if (ingredientRepository.findByINCI(ingredient.getINCI()).isPresent()) {
            throw new IllegalArgumentException("INCI déjà utilisé");
        }


        return ingredientRepository.save(ingredient);
    }

    public List<Ingredient> getAllIngredients() {
        return ingredientRepository.findAll();
    }

    public Optional<Ingredient> getIngredient (long id){
        return ingredientRepository.findById(id);
    }

    public Ingredient updateIngredient(Long id, Ingredient ingredientDetails) {
        Ingredient ingredient = ingredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Ingredient non trouvée avec l'id: " + id));
        ingredient.setNomCommercial(ingredientDetails.getNomCommercial());
        ingredient.setUnite(ingredientDetails.getUnite());
        ingredient.setINCI(ingredientDetails.getINCI());
        ingredient.setFonction(ingredientDetails.getFonction());
        ingredient.setCasN(ingredientDetails.getCasN());
        return ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(Long id) {
        ingredientRepository.deleteById(id);
    }



}
