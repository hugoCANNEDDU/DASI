/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele.service;

import com.google.maps.model.LatLng;
import dao.DaoClient;
import dao.DaoEmploye;
import dao.DaoIntervention;
import dao.DaoPersonne;
import dao.JpaUtil;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import modele.metier.Client;
import modele.metier.Employe;
import modele.metier.Intervention;
import modele.metier.InterventionAnimal;
import modele.metier.InterventionIncident;
import modele.metier.InterventionLivraison;
import modele.metier.Personne;
import static util.DebugLogger.log;
import util.GeoTest;
import util.Message;

/**
 *
 * @author bvachez
 */
public class Service {

    public Service() {
    }

    public static void ajouterClient(Client c) {
        if (TesterAdresse(c)) {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            DaoClient.creerClient(c);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
        }
    }

    public static void ajouterEmploye(Employe e) {
        if (TesterAdresse(e)) {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            DaoEmploye.creerEmploye(e);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
        }
    }

    public static Client trouverClientParMail(String mail) {
        JpaUtil.creerEntityManager();
        Client c = DaoClient.trouverClientParMail(mail);
        JpaUtil.fermerEntityManager();
        return c;
    }

    public static Employe trouverEmployeParMail(String mail) {
        JpaUtil.creerEntityManager();
        Employe e = DaoEmploye.trouverEmployeParMail(mail);
        JpaUtil.fermerEntityManager();
        return e;
    }

    public static List<Client> listerClient() {
        JpaUtil.creerEntityManager();
        List<Client> resultats = DaoClient.listerClient();
        JpaUtil.fermerEntityManager();
        return resultats;
    }

    public static void InscrireClient(Client c) {
        JpaUtil.creerEntityManager();
        String corps = "Bonjour " + c.getPrenom() + ", ";
        try {
            if (!TesterAdresse(c)) {
                corps += " votre inscription au service PROACT'IF a malencontreusement échoué... Adresse invalide.";
            } else if (!DaoClient.clientExistant(c.getMail())) {
                JpaUtil.fermerEntityManager();

                ajouterClient(c);
                corps += "nous vous confirmons votre inscription au service PROACT'IF. Votre numéro de client est : " + c.getIDPersonne() + ".";
            } else {
                corps += " votre inscription au service PROACT'IF a malencontreusement échoué... L'adresse mail saisie est déjà utilisée.";
            }
        } catch (Exception ex) {
            log("Problème lors de la création du profil");
            corps += " votre inscription au service PROACT'IF a malencontreusement échoué... Merci de recommencer ultérieurement.";
        }
        finally
        {
            Message.envoyerMail("contact@proact.if", c.getMail(), "Inscription", corps);
        }
    }
    
    public static void mailDate(String mail, String prenom){
        String corps = "Bonjour " + prenom + ", votre inscription au service PROACT'IF a malencontreusement échoué... Format Date Invalide.";
        Message.envoyerMail("contact@proact.if", mail, "Inscription", corps);
    }

    public static boolean TesterAdresse(Personne c) {
        try {
            Double lat = GeoTest.getLatLng(c.getAdresse()).lat;
            Double lng = GeoTest.getLatLng(c.getAdresse()).lng;
            c.setLat(lat);
            c.setLng(lng);
            return true;
        } catch (Exception ex) {
            log("Adresse inconnue");
            return false;
        }
    }

    public static boolean Authentification(String mail, String mdp) {
        JpaUtil.creerEntityManager();
        boolean b = DaoPersonne.Identification(mail, mdp);
        JpaUtil.fermerEntityManager();
        return b;
    }

    public static void InitEmployes() {
        //Date heureDepart, Date heureFin, boolean disponibilite, String adresse, double lat, double lng, String mail, String mdp
        Employe e1 = new Employe(8, 9, true, "7 Avenue Jean Capelle Ouest, Villeurbanne", 0, 0, "e1@proact.if", "0", "0600000000");
        Employe e2 = new Employe(8, 15, true, "10 Avenue Jean Capelle Ouest, Villeurbanne", 0, 0, "e2@proact.if", "0", "0600000001");
        Employe e3 = new Employe(10, 17, true, "118 Rue Anatole France, Villeurbanne", 0, 0, "e3@proact.if", "0", "0600000002");
        Employe e4 = new Employe(3, 23, true, "Place Lazarre Goujon, Villeurbanne", 0, 0, "e4@proact.if", "0", "0600000003");
        Employe e5 = new Employe(14, 17, true, "36 Rue Michel Dupeuble, Villeurbanne", 0, 0, "e5@proact.if", "0", "0600000004");
        Employe e6 = new Employe(12, 13, true, "157-159 Rue Alexis Perroncel, Villeurbanne", 0, 0, "e6@proact.if", "0", "0600000005");
        Employe e7 = new Employe(7, 15, true, "3 Rue du 8 Mai 1945, Villeurbanne", 0, 0, "e7@proact.if", "0", "0600000006");
        Employe e8 = new Employe(20, 21, true, "115 Avenue Roger Salengro, Villeurbanne", 0, 0, "e8@proact.if", "0", "0600000007");
        Employe e9 = new Employe(7, 14, true, "61 Avenue Roger Salengro, Villeurbanne", 0, 0, "e9@proact.if", "0", "0600000008");
        Employe e10 = new Employe(9, 16, true, "15 Boulevard André Latarjet, Villeurbanne", 0, 0, "e10@proact.if", "0", "0600000009");
        Employe e11 = new Employe(11, 22, true, "1 Rue de la Technologie, Villeurbanne", 0, 0, "e11@proact.if", "0", "0600000010");
        Employe e12 = new Employe(19, 21, true, "7 /9 Rue du Canal, Villeurbanne", 0, 0, "e12@proact.if", "0", "0600000011");
        Employe e13 = new Employe(2, 9, true, "30 Avenue Albert Einstein, Villeurbanne", 0, 0, "e13@proact.if", "0", "0600000012");
        Employe e14 = new Employe(16, 23, true, "3 Avenue Albert Einstein, Villeurbanne", 0, 0, "e14@proact.if", "0", "0600000013");
        Employe e15 = new Employe(11, 15, true, "11 Rue Dr Dolard, Villeurbanne", 0, 0, "e15@proact.if", "0", "0600000014");
        ajouterEmploye(e1);
        ajouterEmploye(e2);
        ajouterEmploye(e3);
        ajouterEmploye(e4);
        ajouterEmploye(e5);
        ajouterEmploye(e6);
        ajouterEmploye(e7);
        ajouterEmploye(e8);
        ajouterEmploye(e9);
        ajouterEmploye(e10);
        ajouterEmploye(e11);
        ajouterEmploye(e12);
        ajouterEmploye(e13);
        ajouterEmploye(e14);
        ajouterEmploye(e15);
    }

    public static Employe employeAssigne(Client c) {
        JpaUtil.creerEntityManager();
        int h = Calendar.HOUR_OF_DAY;
        List<Employe> employesOP = DaoEmploye.listerEmployesOP(h);
        JpaUtil.fermerEntityManager();
        if (employesOP.isEmpty()) {
            return null;
        } else {
            Double duration = 120225.0;
            Double newDuration;
            Employe eChoisi = new Employe();
            for (Employe e : employesOP) {
                newDuration = GeoTest.getTripDurationByBicycleInMinute(new LatLng(c.getLat(), c.getLng()), new LatLng(e.getLat(), e.getLng()));
                if (newDuration < duration) {
                    duration = newDuration;
                    eChoisi = e;
                }
            }
            return eChoisi;
        }
    }

    public static Intervention creerInterventionAnimal(Client c, String nomAnimal, String description) {
        Employe e = employeAssigne(c);
        if (e != null) {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            DaoEmploye.OPToBusy(e);
            Intervention i = new InterventionAnimal(nomAnimal, description, null, null, new Date(), e, c);
            DaoIntervention.creerIntervention(i);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            return i;
        } else {
            return null;
        }

    }

    public static Intervention creerInterventionIncident(Client c, String description) {
        Employe e = employeAssigne(c);
        if (e != null) {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            DaoEmploye.OPToBusy(e);
            Intervention i = new InterventionIncident(description, null, null, new Date(), e, c);
            DaoIntervention.creerIntervention(i);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            return i;
        } else {
            return null;
        }
    }

    public static Intervention creerInterventionLivraison(Client c, String typeObjet, String entreprise, String description) {
        Employe e = employeAssigne(c);
        if (e != null) {
            JpaUtil.creerEntityManager();
            JpaUtil.ouvrirTransaction();
            DaoEmploye.OPToBusy(e);
            Intervention i = new InterventionLivraison(typeObjet, entreprise, description, null, null, new Date(), e, c);
            DaoIntervention.creerIntervention(i);
            JpaUtil.validerTransaction();
            JpaUtil.fermerEntityManager();
            return i;
        } else {
            return null;
        }
    }

    public static Intervention InterventionEnCours(Employe e) {
        JpaUtil.creerEntityManager();
        Intervention i = DaoIntervention.currentIntervention(e);
        JpaUtil.fermerEntityManager();
        return i;
    }

    public static void cloturerIntervention(Employe e, Intervention i, Integer etat, String comment, Integer h, Integer m) {
        Date fin = new Date();
        fin.setHours(h);
        fin.setMinutes(m);
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        DaoEmploye.BusyToOP(e);
        DaoIntervention.finirIntervention(i, etat, comment, fin);
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
    }
    
    public static List<Intervention> listerInterventionsJour (Date date){
        JpaUtil.creerEntityManager();
        List<Intervention> resultats = DaoIntervention.listerInterventionsJour(date);
        JpaUtil.fermerEntityManager();
        return resultats;
    }
    
    public static List<Intervention> listerInterventionsClient(Client c){
        JpaUtil.creerEntityManager();
        List<Intervention> resultats = DaoIntervention.listerInterventionsClient(c);
        JpaUtil.fermerEntityManager();
        return resultats;
    }
    
    public static Intervention getInterventionByID(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Intervention i = DaoIntervention.trouverParID(id); 
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return i;
        
    }
    
    public static Client getClientByID(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Client c = DaoClient.trouverParID(id); 
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return c;
        
    }
    
    public static Employe getEmployeByID(Long id){
        JpaUtil.creerEntityManager();
        JpaUtil.ouvrirTransaction();
        Employe e = DaoEmploye.trouverParID(id); 
        JpaUtil.validerTransaction();
        JpaUtil.fermerEntityManager();
        return e;
        
    }
    
    public static void NotificationClient(Intervention i)
    {
        String message = "Votre demande d'intervention du "+i.getDateIntervention()+ "a été cloturée à "+i.getHeureFin()+". "+i.getCommentaire();
        Message.envoyerNotification(i.getClient().getNumTel(), message);
    } 
    
    public static void NotificationEmploye(Intervention i)
    {
        String message = "Intervention "+i.getType()+" demandée le "+i.getDateIntervention()+" pour "+i.getClient().getPrenom()+" "+i.getClient().getNom()+
                " (#"+i.getClient().getIDPersonne()+"), "+i.getClient().getAdresse()+". <<"+i.getDescription()+" >>.";
        Message.envoyerNotification(i.getEmploye().getNumTel(), message);
    }
}
