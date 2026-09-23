package com.labo.labo.controller;


import com.labo.labo.dto.IngredientDTO;
import com.labo.labo.entity.Formule;
import com.labo.labo.entity.Ingredient;
import com.labo.labo.service.IngredientService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/ingredients")
@CrossOrigin
public class IngredientController {

    @Autowired
    IngredientService ingredientService;
    private final Logger log = LoggerFactory.getLogger(IngredientService.class);

    @PostMapping("/create")
    public Ingredient createIngredient(@RequestBody Ingredient ingredient){
        return ingredientService.createIngredient(ingredient);
    }
    @GetMapping("")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    @GetMapping("/{id}")
    public Optional<Ingredient> getIngredient(@PathVariable Long id ){
        return ingredientService.getIngredient(id);
    }

    @PutMapping("/update/{id}")
    public Ingredient updateIngredient(@RequestBody Ingredient ingredient ,@PathVariable Long id ){
        return ingredientService.updateIngredient(id,ingredient);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteIngredient(@PathVariable Long id){
         ingredientService.deleteIngredient(id);
    }

}
