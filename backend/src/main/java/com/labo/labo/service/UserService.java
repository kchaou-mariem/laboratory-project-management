package com.labo.labo.service;

import com.labo.labo.entity.Famille;
import com.labo.labo.repository.FamilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    FamilleRepository familleRepository;

    public String getUser() {
        Famille famille= new Famille("kchaou");
        return familleRepository.save(famille).getNom();
    }
}
