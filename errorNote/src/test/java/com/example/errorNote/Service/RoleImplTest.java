package com.example.errorNote.Service;

import com.example.errorNote.Modele.Role;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class RoleImplTest {


    @Autowired
    RoleService roleService;
    @Test
    void recupererParIdRole() {
    }

    @Test
    void getLibelleRolee() {
    }

    @Test
    void ajouterRole() {
        Role role = new Role(3L, "Mariam");
        roleService.AjouterRole(role);
    }
}