package com.labo.labo.service;

import com.labo.labo.dto.CompteRoleDTO;
import com.labo.labo.entity.CleRoleCompte;
import com.labo.labo.entity.Phase;
import com.labo.labo.entity.RoleCompte;
import com.labo.labo.factory.CompteRoleFactory;
import com.labo.labo.repository.RoleCompteRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class RoleCompteService {
    private final Logger log = LoggerFactory.getLogger(RoleCompteService.class);

    @Autowired
    RoleCompteRepository roleCompteRepository;

    public RoleCompte createRoleCompte(RoleCompte roleCompte) {
        return roleCompteRepository.save(roleCompte);
    }

    public List<RoleCompte> getAllRoleComptes() {
        return roleCompteRepository.findAll();
    }

    public Optional<RoleCompte> getRoleCompte (long id){
        return roleCompteRepository.findById(id);
    }

    public RoleCompte updateRoleCompte(Long id, RoleCompte roleCompteDetails) {
        RoleCompte roleCompte = roleCompteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("roleCompte non trouvée avec l'id: " + id));
        roleCompte.setRole(roleCompteDetails.getRole());
        return roleCompteRepository.save(roleCompte);
    }

    public void deleteRoleCompte(Long id) {
        roleCompteRepository.deleteById(id);
    }



//    public CompteRoleDTO save(CompteRoleDTO roleCompteDTO) {
//        log.debug("Request to save RoleCompte: {}", roleCompteDTO);
//        RoleCompte roleCompte = CompteRoleFactory.roleCompteDTOToRoleCompte(roleCompteDTO);
//        Optional<RoleCompte> existingRoleCompte=RoleCompteRepository.findById(roleCompte.getIdRoleCompte()); //findById return optional par défaut
//        if(existingRoleCompte.isPresent())
//        { throw new IllegalStateException("RoleCompte existe !");}
//        roleCompte = RoleCompteRepository.save(roleCompte);
//        return CompteRoleFactory.compteRoleToCompteRoleDTO(roleCompte);
//    }
//
//    public CompteRoleDTO update(CompteRoleDTO roleCompteDTO) {
//        log.debug("Request to update RoleCompte: {}", roleCompteDTO);
//        RoleCompte roleCompte = CompteRoleFactory.roleCompteDTOToRoleCompte(roleCompteDTO);
//        Optional<RoleCompte> inBaseOpt = RoleCompteRepository.findById(roleCompte.getIdRoleCompte());
//        if(inBaseOpt.isEmpty())
//        { throw new IllegalStateException("RoleCompte n'existe pas !");}
//        //Preconditions.checkLegalArgument(inBase != null, "service.NotFound");
//        RoleCompte inBase = inBaseOpt.get(); //transformer optional en entité
//        inBase.setRole(roleCompteDTO.getRole());
//        roleCompte = RoleCompteRepository.save(roleCompte);
//        return CompteRoleFactory.compteRoleToCompteRoleDTO(roleCompte);
//    }
//
//    @Transactional(readOnly = true)
//    public CompteRoleDTO getRoleCompteById(CleRoleCompte id) { //return dto
//        log.debug("Request to get RoleCompte: {}", id);
//        Optional<RoleCompte> roleCompteOpt = RoleCompteRepository.findById(id);
//        if(roleCompteOpt.isEmpty())
//        { throw new IllegalStateException("RoleCompte n'existe pas !");}
//        RoleCompte roleCompte=roleCompteOpt.get();
//        return CompteRoleFactory.compteRoleToCompteRoleDTO(roleCompte);
//    }
//
//    @Transactional(readOnly = true)
//    public RoleCompte getRoleCompte(CleRoleCompte id) {   //return entité
//        log.debug("Request to get RoleCompte: {}", id);
//        Optional<RoleCompte> roleCompteOpt = RoleCompteRepository.findById(id);
//        if(roleCompteOpt.isEmpty())
//        { throw new IllegalStateException("RoleCompte n'existe pas !");}
//        return roleCompteOpt.get();
//    }
//
//    @Transactional(readOnly = true)
//    public Collection<CompteRoleDTO> getAll() {
//        log.debug("Request to get All RoleCompte");
//        Collection<RoleCompte> result = RoleCompteRepository.findAll();
//        return CompteRoleFactory.compteRoleToCompteRoleDTOs(result);
//    }
//
//    public void delete(CleRoleCompte id) {
//        log.debug("Request to delete RoleCompte: {}", id);
//        Optional<RoleCompte> roleCompteOpt = RoleCompteRepository.findById(id);
//        if(roleCompteOpt.isEmpty())
//        { throw new IllegalStateException("RoleCompte n'existe pas !");}
//        RoleCompteRepository.deleteById(id);
//    }
//
//    /*private void checkRoleCompteExists(String titre) {
//        if (RoleCompteRepository.findByTitre(titre) != null) {
//            throw new IllegalStateException("RoleCompte with titre " + titre + " already exists");
//        }*/
}
