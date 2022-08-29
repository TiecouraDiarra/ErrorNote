package com.example.errorNote.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "utilisateur")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
//@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public class Utilisateur {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idUtilisateur;
    private String nomUtilisateur;
    private String prenomUtilisateur;
    private String numeroUtilisateur;
    private String emailUtilisateur;
    private String password;


    //================LA CLEF PRIMAIRE DE LA CLASSE ROLE=========================
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_role")
    private Role role;

}
