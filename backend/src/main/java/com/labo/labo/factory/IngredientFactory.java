package com.labo.labo.factory;

import com.labo.labo.dto.IngredientDTO;
import com.labo.labo.entity.Ingredient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IngredientFactory {
    public static IngredientDTO ingredientToIngredientDTO(Ingredient ingredient) {
        IngredientDTO ingredientDTO = new IngredientDTO();
        ingredientDTO.setNomCommercial(ingredient.getNomCommercial());
        return ingredientDTO;
    }

    public static Ingredient ingredientDTOToIngredient(IngredientDTO ingredientDTO) {
        Ingredient ingredient = new Ingredient();
        ingredient.setNomCommercial(ingredientDTO.getNomCommercial());
        return ingredient;
    }

    public static Collection<IngredientDTO> ingredientToIngredientDTOs(Collection<Ingredient> ingredients) {
        List<IngredientDTO> ingredientsDTO = new ArrayList<>();
        ingredients.forEach(x -> ingredientsDTO.add(ingredientToIngredientDTO(x)));
        return ingredientsDTO;
    }
}
