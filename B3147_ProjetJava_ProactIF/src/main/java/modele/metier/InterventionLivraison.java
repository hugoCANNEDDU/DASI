/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.util.Date;
import javax.persistence.Entity;

/**
 *
 * @author bvachez
 */
@Entity
public class InterventionLivraison extends Intervention {
    
    protected String typeObjet;

    public InterventionLivraison() {
    }
    
    protected String entreprise;

    public InterventionLivraison(String typeObjet, String entreprise, String description, Date heureFin, String commentaire, Date dateIntervention, Employe employeAffecte, Client clientConcerné) {
        super(description, heureFin, commentaire, dateIntervention, employeAffecte, clientConcerné);
        this.typeObjet = typeObjet;
        this.entreprise = entreprise;
    }

    public String getTypeObjet() {
        return typeObjet;
    }

    public void setTypeObjet(String typeObjet) {
        this.typeObjet = typeObjet;
    }

    public String getEntreprise() {
        return entreprise;
    }

    public void setEntreprise(String entreprise) {
        this.entreprise = entreprise;
    }
    
    public String getType(){
        return "Livraison";
    }
}
