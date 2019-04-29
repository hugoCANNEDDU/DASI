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
public class InterventionAnimal extends Intervention {
    
    protected String nomAnimal;

    public InterventionAnimal(String nomAnimal, String description, Date heureFin, String commentaire, Date dateIntervention, Employe employeAffecte, Client clientConcerné) {
        super(description, heureFin, commentaire, dateIntervention, employeAffecte, clientConcerné);
        this.nomAnimal = nomAnimal;
    }

    public String getNomAnimal() {
        return nomAnimal;
    }

    public void setNomAnimal(String nomAnimal) {
        this.nomAnimal = nomAnimal;
    }
    
    public String getType(){
        return "Animal";
    }

    public InterventionAnimal(String description, Date heureFin, String commentaire, Date dateIntervention, Employe employeAffecte, Client clientConcerné) {
        super(description, heureFin, commentaire, dateIntervention, employeAffecte, clientConcerné);
    }

    public InterventionAnimal() {
    }
    
    
    
}
