package com.labo.labo.repository;

import com.labo.labo.entity.FormuleIngredient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FormuleIngredientRepository extends JpaRepository<FormuleIngredient,Long> {
    void deleteByFormuleId(Long formuleId);
}
