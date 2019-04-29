/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import com.google.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author bvachez
 */
@Entity
public class Client extends Personne {
    
    protected String nom;

    public Client(String nom, String prenom, String civilite, Date dateNaissance, String adresse, double lat, double lng, String mail, String mdp, String numTel) {
        super(adresse, lat, lng, mail, mdp, numTel);
        this.nom = nom;
        this.prenom = prenom;
        this.civilite = civilite;
        this.dateNaissance = dateNaissance;
    }

    protected String prenom;

    protected String civilite;

    @Temporal(DATE)
    protected Date dateNaissance;
    
    @OneToMany
    protected List<Intervention> requetes;

    public Client() {
    }

    public String getCivilite() {
        return civilite;
    }

    public void setCivilite(String civilite) {
        this.civilite = civilite;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public boolean isEmploye()
    {
        return false;
    }
}
