package com.labo.labo.repository;

import com.labo.labo.entity.Ingredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IngredientRepository extends JpaRepository<Ingredient,Long> {
    //Ingredient findByNomCommercial(String nom);
    Optional<Ingredient> findByNomCommercial(String nom);
    Optional<Ingredient> findByCasN(String nom);
    Optional<Ingredient> findByINCI(String nom);

}