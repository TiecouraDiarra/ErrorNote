package com.example.errorNote.Modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "commentaire")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Commentaire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idComm;
    private String commentaire;

    //================LA CLEF PRIMAIRE DE LA CLASSE SOLUTION=========================
    @ManyToOne
    @JoinColumn(name = "id_solution")
    private Solution solution;

    //================LA CLEF PRIMAIRE DE LA CLASSE UTILISATEUR=========================
    @ManyToOne
    @JoinColumn(name = "id_utilisateurs")
    private Utilisateur AutresUtilisateurs;
}
