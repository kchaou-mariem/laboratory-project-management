package com.labo.labo.controller;


import com.labo.labo.entity.FormuleIngredient;
import com.labo.labo.service.FormuleIngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/formuleIngredients")
@CrossOrigin
public class FormuleIngredientController {
    @Autowired
    FormuleIngredientService formuleIngredientService;

    @PostMapping("/create")
    public FormuleIngredient createFormuleIngredient(@RequestBody FormuleIngredient forming){
        return formuleIngredientService.createFormuleIngredient(forming);
    }
    @GetMapping("")
    public List<FormuleIngredient> getAllFormuleIngredients(){

        return formuleIngredientService.getAllFormuleIngredient();
    }
    @GetMapping("/{id}")
    public Optional<FormuleIngredient> getFormuleIngredient(@PathVariable Long id ){
        return formuleIngredientService.getFormuleIngredient(id);
    }
    @PutMapping("/update/{id}")
    public FormuleIngredient updateFormuleIngredient(@RequestBody FormuleIngredient forming , @PathVariable Long id ){
        return formuleIngredientService.updateFormuleIngredient(id,forming);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteFormule(@PathVariable Long id){

        formuleIngredientService.deleteFormuleIngredient(id);
    }
}
