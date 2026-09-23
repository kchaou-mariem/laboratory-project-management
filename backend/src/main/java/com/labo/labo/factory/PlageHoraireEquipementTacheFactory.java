package com.labo.labo.factory;

import com.labo.labo.dto.PlageHoraireEquipementTacheDTO;
import com.labo.labo.entity.PlageHoraireEquipementTache;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PlageHoraireEquipementTacheFactory {
    public static PlageHoraireEquipementTacheDTO plageHoraireEquipementTacheToDTO(PlageHoraireEquipementTache plageHoraireEquipementTache) {
        PlageHoraireEquipementTacheDTO dto = new PlageHoraireEquipementTacheDTO();
        dto.setTache(plageHoraireEquipementTache.getTache());
        dto.setEquipement(plageHoraireEquipementTache.getEquipement());
        dto.setHoraireD(plageHoraireEquipementTache.getHoraireD());
        dto.setHoraireF(plageHoraireEquipementTache.getHoraireF());
        return dto;
    }
    public static PlageHoraireEquipementTache plageHoraireEquipementTacheDTOToPlageHoraireEquipementTache(PlageHoraireEquipementTacheDTO dto) {
        PlageHoraireEquipementTache plageHoraireEquipementTache = new PlageHoraireEquipementTache();
        plageHoraireEquipementTache.setTache(dto.getTache());
        plageHoraireEquipementTache.setEquipement(dto.getEquipement());
        plageHoraireEquipementTache.setHoraireD(dto.getHoraireD());
        plageHoraireEquipementTache.setHoraireF(dto.getHoraireF());
        return plageHoraireEquipementTache;
    }
    public static Collection<PlageHoraireEquipementTacheDTO> plageHoraireEquipementTacheToDTOs(Collection<PlageHoraireEquipementTache> plages) {
        List<PlageHoraireEquipementTacheDTO> dtos = new ArrayList<>();
        plages.forEach(plage -> dtos.add(plageHoraireEquipementTacheToDTO(plage)));
        return dtos;
    }


}
