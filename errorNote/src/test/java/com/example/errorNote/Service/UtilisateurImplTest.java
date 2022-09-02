package com.example.errorNote.Service;

import com.example.errorNote.Modele.Role;
import com.example.errorNote.Modele.Utilisateur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilisateurImplTest {

    @Autowired
    private UtilisateurService utilisateurService;
    @Autowired
    private RoleService roleService;


    @Test
    void ajouterUtilisateur() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmailUtilisateur("tiecou@gmail.com");
        Utilisateur u = utilisateurService.TrouverParEmail(utilisateur.getEmailUtilisateur());
        Role role = roleService.getLibelleRolee("USER");
        if (u == null ) {
            utilisateur.setRole(role);
            utilisateur.setNomUtilisateur("");
            utilisateur.setPrenomUtilisateur("");
            utilisateur.setPassword("tiec");
            utilisateurService.AjouterUtilisateur(utilisateur);

        } else {
            System.out.println("Cet utilisateur existe deja!!");
        }
    }

    @Test
    void ajouterAdmin() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmailUtilisateur("mary@gmail.com");
        Utilisateur u = utilisateurService.TrouverParEmail(utilisateur.getEmailUtilisateur());
        Utilisateur admin = utilisateurService.RecupererParId(1L);
        Role role = roleService.getLibelleRolee("ADMIN");
        if (u == null) {
            if (admin != null && admin.getRole() == role) {
                utilisateur.setRole(role);
                utilisateur.setNomUtilisateur("");
                utilisateur.setPrenomUtilisateur("");
                utilisateur.setPassword("");
                utilisateurService.AjouterUtilisateur(utilisateur);
            } else {
                System.out.println("Vous essayez d'attribuez un role qui n'exsite pas!");
            }
        } else {
            System.out.println("Cet utilisateur existe deja!!");
        }
    }

    @Test
    void recupererParId() {
    }

    @Test
    void supprimer() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmailUtilisateur("tiec@gmail.com");
        //utilisateur.setPassword("mary");
        Utilisateur user1 = utilisateurService.TrouverParEmail(utilisateur.getEmailUtilisateur());
        Role role1 = roleService.RecupererParIdRole(1L);
        if (user1==null){
            System.out.println("Email Incorrect!");
        }
        else if (!user1.getPassword().equals("admin")) {
            System.out.println("Mot de passe incorrect!");
        } else if (role1!=null && role1.getLibelleRole().equals("ADMIN")){
             utilisateurService.Supprimer(9L);
        }else {
            System.out.println("Vous n'avez pas le droit de supprimer un utilisateur");
        }
    }

    @Test
    void modifier() {
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setEmailUtilisateur("tiec@gmail.com");
        Utilisateur user1 = utilisateurService.TrouverParEmail(utilisateur.getEmailUtilisateur());
        Role role1 = roleService.RecupererParIdRole(1L);
        if (user1==null){
            System.out.println("Email Incorrect!");
        }
        else if (!user1.getPassword().equals("admin")) {
            System.out.println("Mot de passe incorrect!");
        }else if (role1!=null && role1.getLibelleRole().equals("ADMIN")){
            utilisateur.setNomUtilisateur("");
            utilisateur.setPrenomUtilisateur("");
            utilisateur.setEmailUtilisateur("");
            utilisateur.setNumeroUtilisateur("");
            utilisateur.setPassword("");
            utilisateurService.modifier(1L, utilisateur);
            System.out.println("Utilisateur modifié avec succès");
        }

    }

    @Test
    void trouverParEmail() {
    }

    @Test
    void afficherTousLesUtilisateurs() {
    }

    @Test
    void afficherTousLesAdmin() {
    }

    @Test
    void afficherTousLesUserSimple() {
    }
}