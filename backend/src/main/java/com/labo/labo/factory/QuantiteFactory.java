package com.labo.labo.factory;

import com.labo.labo.dto.QuantiteDTO;
import com.labo.labo.entity.Quantite;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class QuantiteFactory {
    public static QuantiteDTO quantiteToQuantiteDTO(Quantite quantite) {
        QuantiteDTO quantiteDTO = new QuantiteDTO();
        quantiteDTO.setFormule(quantite.getFormule());
        quantiteDTO.setIngredient(quantite.getIngredient());
        quantiteDTO.setQuantite(quantite.getQuantite());
        return quantiteDTO;
    }

    public static Quantite quantiteDTOToQuantite(QuantiteDTO quantiteDTO) {
        Quantite quantite = new Quantite();
        quantite.setFormule(quantiteDTO.getFormule());
        quantite.setIngredient(quantiteDTO.getIngredient());
        quantite.setQuantite(quantiteDTO.getQuantite());
        return quantite;
    }

    public static Collection<QuantiteDTO> quantiteToQuantiteDTOs(Collection<Quantite> quantites) {
        List<QuantiteDTO> quantitesDTO = new ArrayList<>();
        quantites.forEach(x -> quantitesDTO.add(quantiteToQuantiteDTO(x)));
        return quantitesDTO;
    }
}
