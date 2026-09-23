package com.labo.labo.service;

import com.labo.labo.entity.FormuleIngredient;
import com.labo.labo.repository.FormuleIngredientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormuleIngredientService {
    @Autowired
    FormuleIngredientRepository formuleIngredientRepository;
    public FormuleIngredient createFormuleIngredient(FormuleIngredient forming) {
        return formuleIngredientRepository.save(forming);
    }

    public List<FormuleIngredient> getAllFormuleIngredient() {
        return formuleIngredientRepository.findAll();
    }

    public Optional<FormuleIngredient> getFormuleIngredient(Long id) {
        return formuleIngredientRepository.findById(id);
    }

    public FormuleIngredient updateFormuleIngredient(Long id, FormuleIngredient forming) {
        FormuleIngredient formule_ingredient = formuleIngredientRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Formule_ingredinet non trouvée avec l'id: " + id));
        formule_ingredient.setQuantite(forming.getQuantite());
        return formuleIngredientRepository.save(formule_ingredient);
    }

    public void deleteFormuleIngredient(Long id) {
        formuleIngredientRepository.deleteById(id);
    }
}
