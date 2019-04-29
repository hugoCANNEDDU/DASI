/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.util.List;
import javax.persistence.Query;
import modele.metier.Personne;

/**
 *
 * @author bvachez
 */
public class DaoPersonne {

    public static boolean Identification(String mail, String mdp) {
        String jpql = "select p from Personne p where p.mail= :mail and p.mdp=:mdp";
        Query query = JpaUtil.obtenirEntityManager().createQuery(jpql);
        query.setParameter("mail", mail);
        query.setParameter("mdp", mdp);
        List<Personne> resultats = (List<Personne>) query.getResultList();
        return !resultats.isEmpty();
    }
}
