package com.example.errorNote.Modele;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "solution")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Solution {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idSolution;
    private String descriptionSolution;
    private String ressources;
    private String technologie;
    private String MethodologieAdop;
    private Date dateSolution;

    //================LA CLEF PRIMAIRE DE LA CLASSE PROBLEME=========================
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "id_probleme")
    private Probleme probleme;

    @OneToMany(mappedBy = "solution")
    @JsonIgnore
    List<Commentaire> commentaireList = new ArrayList<>();
}
