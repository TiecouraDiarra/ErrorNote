package com.example.errorNote.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "probleme")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Probleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idProbleme;
    private String titreProbleme;
    private String descriptionProbleme;
    private Date dateProbleme;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_utilisateur")
    private Utilisateur utilisateur;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "id_etat")
    private EtatProbleme Etat;


}
