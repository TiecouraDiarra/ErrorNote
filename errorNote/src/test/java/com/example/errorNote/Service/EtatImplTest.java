package com.example.errorNote.Service;

import com.example.errorNote.Modele.EtatProbleme;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class EtatImplTest {

    @Autowired
    EtatService etatService;

    @Test
    void recupererParIdEtat() {
    }

    @Test
    void ajouterEtat() {
        EtatProbleme etatProbleme =new  EtatProbleme(8L,"Kadi");
        etatService.AjouterEtat(etatProbleme);
    }
}