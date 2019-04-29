/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.metier;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 *
 * @author bvachez lclemenceau
 */
@Entity
public class Employe extends Personne {
    
    protected Integer heureDepart;
    
    protected Integer heureFin;
    
    protected boolean disponibilite;
    
    @OneToMany
    protected List<Intervention> interventionsClient;

    public Employe(Integer heureDepart, Integer heureFin, boolean disponibilite, String adresse, double lat, double lng, String mail, String mdp, String numTel) {
        super(adresse, lat, lng, mail, mdp, numTel);
        this.heureDepart = heureDepart;
        this.heureFin = heureFin;
        this.disponibilite = disponibilite;
    }

    public Employe() {
    }
    
    public Integer getHeureDepart() {
        return heureDepart;
    }

    public void setHeureDepart(Integer heureDepart) {
        this.heureDepart = heureDepart;
    }

    public Integer getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(Integer heureFin) {
        this.heureFin = heureFin;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public boolean isEmploye()
    {
        return true;
    }
}
