package com.example.errorNote.Modele;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "etatprobleme")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EtatProbleme {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idEtat;
    private String Etat;
}
