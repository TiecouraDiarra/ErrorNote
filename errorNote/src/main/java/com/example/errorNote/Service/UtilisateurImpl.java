package com.example.errorNote.Service;

import com.example.errorNote.Modele.Utilisateur;
import com.example.errorNote.Repository.RepoProbleme;
import com.example.errorNote.Repository.RepoUtilisateur;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
@Data
public class UtilisateurImpl implements UtilisateurService {

    //================LA DEPENDANCE DU REPOSITORY DES UTILISATEURS=========================
    @Autowired
    private RepoUtilisateur repoUtilisateur;

    //================LA DEPENDANCE DU REPOSITORY DES PROBLEMES=========================
    @Autowired
    private RepoProbleme repoProbleme;

    //================DEBUT DE LA METHODE PERMETTANT D'AJOUTER UN UTILISATEUR=========================
    @Override
    public Utilisateur AjouterUtilisateur(Utilisateur utilisateur) {

        return repoUtilisateur.save(utilisateur);
    }
    //================FIN DE LA METHODE PERMETTANT D'AJOUTER UN UTILISATEUR=========================


    //================DEBUT DE LA METHODE PERMETTANT RECUPERER L'IDENTIFIANT D'UN UTILISATEUR=========================
    @Override
    public Utilisateur RecupererParId(Long idUtilisateur) {
        try {
            return repoUtilisateur.findById(idUtilisateur).get();

        }catch (Exception e){
            return  null;
        }
    }
    //================FIN DE LA METHODE PERMETTANT RECUPERER L'IDENTIFIANT D'UN UTILISATEUR=========================

    //================DEBUT DE LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR=========================
    @Override
    public String Supprimer(Long idUtilisateur) {
        repoUtilisateur.deleteById(idUtilisateur);
        return "Utilisateur supprimé avec succès";
    }
    //================FIN DE LA METHODE PERMETTANT DE SUPPRIMER UN UTILISATEUR=========================


    //================DEBUT DE LA METHODE PERMETTANT DE MODIFIER UN UTILISATEUR=========================
    @Override
    public Utilisateur modifier(Long idUtilisateur, Utilisateur utilisateur) {
        return repoUtilisateur.findById(idUtilisateur)
                .map(p->{
                    p.setNomUtilisateur(utilisateur.getNomUtilisateur());
                    p.setPrenomUtilisateur(utilisateur.getPrenomUtilisateur());
                    p.setEmailUtilisateur(utilisateur.getEmailUtilisateur());
                    p.setNumeroUtilisateur(utilisateur.getNumeroUtilisateur());
                    p.setPassword(utilisateur.getPassword());
                    return repoUtilisateur.save(p);
                }).orElseThrow(() -> new RuntimeException("utilisateur non trouvé !"));
    }
    //================FIN DE LA METHODE PERMETTANT DE MODIFIER UN UTILISATEUR=========================


    //================DEBUT DE LA METHODE PERMETTANT DE RECUPERER L'EMAIL D'UN UTILISATEUR=========================
    /*@Override
    public Utilisateur TrouverParEmail(String emailUtilisateur) {
        return repoUtilisateur.findByEmail(emailUtilisateur);
    }*/
    //================FIN DE LA METHODE PERMETTANT DE RECUPERER L'EMAIL D'UN UTILISATEUR=========================
}
