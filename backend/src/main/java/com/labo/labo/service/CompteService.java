package com.labo.labo.service;


import com.labo.labo.entity.Compte;
import com.labo.labo.enumeration.Role;
import com.labo.labo.repository.CompteRepository;
//import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.time.LocalDate;
import java.time.chrono.ChronoLocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CompteService {

    //private final Logger log = LoggerFactory.getLogger(CompteService.class);

    @Autowired
    CompteRepository compteRepository;
    public Compte createCompte(Compte compte) {

        return compteRepository.save(compte);
    }

    public List<Compte> getAllComptes() {
        return compteRepository.findAll();
    }

    public Optional<Compte> getCompte (long id){

        return compteRepository.findById(id);
    }
    public Optional<Compte> getCompteByEmail(String email){
        return compteRepository.findByEmail(email);
    }
//    public boolean verifConnexion(Map<String,String>cpt){
//        String email=cpt.get("email");
//        String password=cpt.get("password");
//        Optional<Compte>compteOptional=compteRepository.findByEmail(email);
//        if(compteOptional.isPresent()){
//            if (compteOptional.get().getPassword().equals(password)){
//                return true;
//            }
//            return false;
//        }
//        return false;
//    }

    public Optional<Compte> verifConnexion(Map<String,String>cpt){
        String email=cpt.get("email");
        String password=cpt.get("password");
        Optional<Compte>compteOptional=compteRepository.findByEmail(email);
        if(compteOptional.isPresent()){
            if (compteOptional.get().getPassword().equals(password)){
                return compteOptional;
            }
            return Optional.empty();
        }
        return Optional.empty();
    }
    public Compte updateCompte(Long id, Compte detailsCompte) {
        Compte compte = compteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("DetailsPhase non trouvée avec l'id: " + id));
        compte.setPassword(detailsCompte.getPassword());
        compte.setEmail(detailsCompte.getEmail());
        compte.setNom(detailsCompte.getNom());
        compte.setPrenom(detailsCompte.getPrenom());
//        compte.setDateNaissance(detailsCompte.getDateNaissance());
        compte.setProjets(detailsCompte.getProjets());
        compte.setRole(detailsCompte.getRole());

//        if (compte.getDateNaissance().isBefore(LocalDate.now())){
//            throw new IllegalArgumentException("date de naissance est incorrecte!! ");
//        }
        return compteRepository.save(compte);
    }
   public List<Compte> ListChefProjet(){
        List<Compte> comptes=compteRepository.findAll();
        List<Compte> chefsProjet=comptes.stream().filter(compte->compte.getRole().equals(Role.CHEF_PROJET))
                .collect(Collectors.toList());

 return chefsProjet;
    }

    public void deleteCompte(Long id) {
        compteRepository.deleteById(id);
    }

}
