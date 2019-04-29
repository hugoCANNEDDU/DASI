/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.metier.Client;
import modele.metier.Personne;
import util.DebugLogger;

/**
 *
 * @author bvachez
 */
public class DaoClient {

    public static List<Client> listerClientParMail(String mail) {
        String jpql = "select c from Client c where c.mail= :mail";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("mail", mail);
        List<Client> resultats = (List<Client>) query.getResultList();
        return resultats;
    }

    public static Client trouverClientParMail(String mail) {
        List<Client> lc = listerClientParMail(mail);
        if (!lc.isEmpty()) {
            return lc.get(0);
        } else {
            return null;
        }
    }

    public static List<Client> listerClient() {
        String jpql = "select c from Client c";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        List<Client> resultats = (List<Client>) query.getResultList();
        return resultats;
    }

    public static void creerClient(Client c) {
        JpaUtil.obtenirEntityManager().persist(c);
    }

    public static boolean clientExistant(String mailC) {
        List<Client> resultats = listerClientParMail(mailC);
        return !resultats.isEmpty();
    }

    public static Client trouverParID(Long id) {
        String jpql = "select c from Client c where c.idPersonne = :id";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("id", id);
        List<Client> resultats = (List<Client>) query.getResultList();
        if (resultats.isEmpty()) {
            return null;
        } else {
            return resultats.get(0);
        }

    }

}
