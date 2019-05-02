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
public class InterventionIncident extends Intervention {

    public InterventionIncident() {
    }

    public InterventionIncident(String description, Date heureFin, String commentaire, Date dateIntervention, Employe employeAffecte, Client clientConcerné) {
        super(description, heureFin, commentaire, dateIntervention, employeAffecte, clientConcerné);
    }

    public String getType() {
        return "Incident";
    }
    
    
}
