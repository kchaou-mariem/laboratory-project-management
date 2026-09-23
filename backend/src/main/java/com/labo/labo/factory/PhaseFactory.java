package com.labo.labo.factory;

import com.labo.labo.dto.CompteRoleDTO;
import com.labo.labo.dto.MarqueDTO;
import com.labo.labo.dto.PhaseDTO;
import com.labo.labo.entity.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PhaseFactory {
    public static PhaseDTO phaseToPhaseDTO(Phase phase) {
        PhaseDTO phaseDTO = new PhaseDTO();
//        phaseDTO.setNom(phase.getNom());


        return phaseDTO;
    }

    public static Phase phaseDTOToPhase(PhaseDTO phaseDTO) {
        Phase phase = new Phase();
//        phase.setNom(phaseDTO.getNom());

        return phase;
    }

    public static Collection<PhaseDTO> phaseToPhaseDTOs(Collection<Phase> phases) {
        List<PhaseDTO> phasesDTO = new ArrayList<>();
        phases.forEach(x -> phasesDTO.add(phaseToPhaseDTO(x)));
        return phasesDTO;
    }
}
