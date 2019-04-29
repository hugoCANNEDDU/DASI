/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.metier.Client;
import modele.metier.Employe;
import modele.metier.Intervention;

/**
 *
 * @author lclemencea
 */
public class DaoIntervention {

    public static void creerIntervention(Intervention i) {
        JpaUtil.obtenirEntityManager().persist(i);
    }

    public static Intervention currentIntervention(Employe e) {
        String jpql = "select i from Intervention i where i.employe=:emp and i.statut='en cours'";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("emp", e);
        List<Intervention> resultats = (List<Intervention>) query.getResultList();
        return resultats.get(0);
    }

    public static void finirIntervention(Intervention i, Integer etat, String comment, Date fin) {
        EntityManager manager = JpaUtil.obtenirEntityManager();       
        Intervention i2 = manager.find(Intervention.class, i.getIdIntervention());
        manager.persist(i2);
        i2.setCommentaire(comment);
        i.setCommentaire(comment);
        i2.setHeureFin(fin);
        i.setHeureFin(fin);
        if (etat == 0) {
            i2.setStatut("echec");
            i.setStatut("echec");
        } else {
            i2.setStatut("reussite");
            i.setStatut("reussite");
        }

    }
    
    public static List<Intervention> listerInterventionsJour(Date date){
        String jpql = "select i from Intervention i where i.dateIntervention=:date";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("date", date);
        List<Intervention> resultats = (List<Intervention>) query.getResultList();
        return resultats;
    }
    
    public static List<Intervention> listerInterventionsClient(Client c){
        String jpql = "select i from Intervention i where i.client=:client";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("client", c);
        List<Intervention> resultats = (List<Intervention>) query.getResultList();
        return resultats;
        
    }

    public static Intervention trouverParID(Long id) {
        String jpql = "select i from Intervention i where i.idPIntervention = :id";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("id", id);
        List<Intervention> resultats = (List<Intervention>) query.getResultList();
        if (resultats.isEmpty()) {
            return null;
        } else {
            return resultats.get(0);
        }
    }
}
