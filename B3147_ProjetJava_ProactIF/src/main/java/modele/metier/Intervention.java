/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;
import static javax.persistence.TemporalType.TIME;

/**
 *
 * @author bvachez
 */
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class Intervention {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long IdIntervention;
    
    protected String description;
    
    protected String statut = "en cours";
    
    @Temporal(TIME)
    protected Date heureFin;
    
    protected String commentaire;
    
    @Temporal(DATE)
    protected Date dateIntervention;

    public Intervention() {
    }
    
    @ManyToOne
    protected Employe employe;
    
    @ManyToOne
    protected Client client;

    public Intervention(String description, Date heureFin, String commentaire, Date dateIntervention, Employe employeAffecte, Client clientConcerné) {
        this.description = description;
        this.heureFin = heureFin;
        this.commentaire = commentaire;
        this.dateIntervention = dateIntervention;
        this.employe = employeAffecte;
        this.client = clientConcerné;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    public Date getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Date heureFin) {
        this.heureFin = heureFin;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }

    public Date getDateIntervention() {
        return dateIntervention;
    }

    public void setDateIntervention(Date dateIntervention) {
        this.dateIntervention = dateIntervention;
    }

    public Long getIdIntervention() {
        return IdIntervention;
    }

    public Employe getEmploye() {
        return employe;
    }

    public void setEmploye(Employe employe) {
        this.employe = employe;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
    
    public abstract String getType();
    
       
}
