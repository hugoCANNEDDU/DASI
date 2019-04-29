/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;
import modele.metier.Client;
import modele.metier.Employe;

/**
 *
 * @author bvachez
 */
public class DaoEmploye {

    public static List<Employe> listerEmployeParMail(String mail) {
        String jpql = "select c from Employe c where c.mail= :mail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("mail", mail);
        List<Employe> resultats = (List<Employe>) query.getResultList();
        return resultats;
    }

    public static void creerEmploye(Employe e) {
        JpaUtil.obtenirEntityManager().persist(e);
    }

    public static void OPToBusy(Employe e) {
        EntityManager manager = JpaUtil.obtenirEntityManager();
        Employe e2 = manager.find(Employe.class, e.getIDPersonne());
        manager.persist(e2);
        e2.setDisponibilite(false);
        e.setDisponibilite(false);
    }

    public static void BusyToOP(Employe e) {
        EntityManager manager = JpaUtil.obtenirEntityManager();
        Employe e2 = manager.find(Employe.class, e.getIDPersonne());
        manager.persist(e2);
        e2.setDisponibilite(true);
        e.setDisponibilite(true);

    }

    public static List<Employe> listerEmployesOP(Integer h) {
        String jpql = "select e from Employe e where e.disponibilite=true and e.heureDepart <= :hour and e.heureFin > :hour";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("hour", h);
        List<Employe> resultats = (List<Employe>) query.getResultList();
        return resultats;
    }

    public static Employe trouverEmployeParMail(String mail) {
        List<Employe> le = listerEmployeParMail(mail);
        if (!le.isEmpty()) {
            return le.get(0);
        } else {
            return null;
        }
    }

    public static Employe trouverParID(Long id) {
        String jpql = "select e from Employe e where e.idPersonne = :id";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("id", id);
        List<Employe> resultats = (List<Employe>) query.getResultList();
        if (resultats.isEmpty()) {
            return null;
        } else {
            return resultats.get(0);
        }

    }
}
