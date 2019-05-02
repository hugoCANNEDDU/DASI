/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import com.google.maps.model.LatLng;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

/**
 *
 * @author bvachez
 */
@Entity
@Inheritance (strategy = InheritanceType.SINGLE_TABLE)
public abstract class Personne {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long IDPersonne;
    
    protected String adresse;
    
    protected String numTel;

    protected double lat;
    
    protected double lng;
    
    protected String mail;
    
    protected String mdp;

    public String getAdresse() {
        return adresse;
    }

    public Long getIDPersonne() {
        return IDPersonne;
    }

    public String getMail() {
        return mail;
    }

    public String getMdp() {
        return mdp;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }
    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }

    public Personne(String adresse, double lat, double lng, String mail, String mdp, String numTel) {
        this.adresse = adresse;
        this.lat = lat;
        this.lng = lng;
        this.mail = mail;
        this.mdp = mdp;
        this.numTel= numTel;
    }

    public String getNumTel() {
        return numTel;
    }

    public void setNumTel(String numTel) {
        this.numTel = numTel;
    }


    public Personne() {
    }
     
    public abstract boolean isEmploye();
}
