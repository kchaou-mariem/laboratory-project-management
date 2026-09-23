package com.labo.labo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data //setters and getters
@AllArgsConstructor
@NoArgsConstructor
@Embeddable
public class CleQuantite implements Serializable {
   private Long idFormule;
   private Long idIngredient;

}
