/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vue;

import dao.JpaUtil;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;
import modele.metier.Client;
import modele.service.Service;
import static util.DebugLogger.log;
import util.GeoTest;
import util.Message;
import util.Saisie;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import javax.persistence.EntityManager;
import modele.metier.Employe;
import modele.metier.Intervention;
import modele.metier.Personne;

/**
 *
 * @author bvachez
 */
public class Main {

    public static void main(String[] args) {

        JpaUtil.init();
        // La ligne Init() ci-dessous est à décommenter pour la première éxecution (initialisation des Employés)
        // NB : L'inscription des clients peut se faire depuis l'un des comptes employé
        //
        // Connexion Employé :
        // Mail : eX@proact.if ou X est compris entre 1 et 15 inclus
        // MdP : 0
        //Init();
        boolean connecte = false;
        String mail = null;
        while (!connecte) {
            mail = Authentifier();
            connecte = (mail != null);
        }
        Client c = Service.trouverClientParMail(mail);
        Employe e = null;
        if (c == null) {
            e = Service.trouverEmployeParMail(mail);
        }
        while (connecte) {
            if (c != null) {
                connecte = ChoisirOptionClient(c);
            } else {
                connecte = ChoisirOptionEmploye(e);
            }
        }
        JpaUtil.destroy();
    }

    public static void ListerClient() {
        List<Client> liste = Service.listerClient();
        for (Client c : liste) {
            AfficherClient(c);
        }
    }

    public static void AfficherClient(Client c) {
        System.out.println(c.getNom() + " " + c.getPrenom() + " " + c.getCivilite() + " " + c.getDateNaissance());
    }

    /* public static void AjoutClient() {
        String adr1 = "7 Avenue Jean Capelle Ouest, Villeurbanne";
        Client c1 = new Client("Nom", "Prenom", "Mr", "10/02/2001", adr1, GeoTest.getLatLng(adr1).lat, GeoTest.getLatLng(adr1).lng, "nomprenom@gmail.com", "0000");
        Client c2 = new Client("Nom", "Prenom", "Mr", "10/02/2001", adr1, GeoTest.getLatLng(adr1).lat, GeoTest.getLatLng(adr1).lng, "nomprenom@gmail.com", "0000");
        Service service = new Service();

        service.ajouterClient(c1);
        service.ajouterClient(c2);

    }*/
    
    public static void InscrireClient() {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String nom = Saisie.lireChaine("Nom : ");
        String prenom = Saisie.lireChaine("Prenom : ");
        String date = Saisie.lireChaine("Date de naissance (JJ/MM/AA): ");
        String civ = Saisie.lireChaine("Civilite : ");
        String adresse = Saisie.lireChaine("Adresse : ");
        String mail = Saisie.lireChaine("Mail : ");
        String mdp = Saisie.lireChaine("Mdp : ");
        String tel = Saisie.lireChaine("Tel : ");
        try {
            Client c = new Client(nom, prenom, civ, format.parse(date), adresse, 0, 0, mail, mdp, tel);
            Service.InscrireClient(c); 
        } catch (Exception ex) {
            Service.mailDate(mail, prenom);
        }

    }

    public static String Authentifier() {
        String mail = Saisie.lireChaine("Mail : ");
        String mdp = Saisie.lireChaine("Mdp : ");
        if (Service.Authentification(mail, mdp)) {
            System.out.println("Authentification réussie");
            return mail;
        } else {
            System.out.println("Authentification échouée");
            return null;
        }
    }

    /*public static void ChoisirOption()
    {
        
    }*/
    public static void DemanderIntervention(Client c) {
        String typeIntervention = "Undefined";
        String description="Undefined";
        Intervention i = null;
        while (!typeIntervention.equals("Animal") && !typeIntervention.equals("Incident") && !typeIntervention.equals("Livraison") && !typeIntervention.equals("Retour")) {
            typeIntervention = Saisie.lireChaine("Entrez le type d'intervention (Animal/Incident/Livraison) ou Retour pour annuler :");
        }
        if (typeIntervention.equals("Animal")) {
            String animal = Saisie.lireChaine("Entrez l'espece de votre animal : ");
            description = Saisie.lireChaine("Décrivez nous votre problème : ");
            i = Service.creerInterventionAnimal(c, animal, description);
        } else if (typeIntervention.equals("Incident")) {
            description = Saisie.lireChaine("Décrivez nous votre problème : ");
            i = Service.creerInterventionIncident(c, description);
        } else if (typeIntervention.equals("Livraison")) {
            String colis = Saisie.lireChaine("Type d'objet à recevoir : ");
            String entreprise = Saisie.lireChaine("Entreprise chargée de la livraison : ");
            description = Saisie.lireChaine("Décrivez nous votre problème : ");
            i = Service.creerInterventionLivraison(c, colis, entreprise, description);
        }
        if (i != null) {
            Service.NotificationEmploye(i);
            System.out.println("Votre demande va etre traitée par un de nos employés.");
        } else {
            System.out.println("Aucun employé ne peut repondre a votre requete.");
        }
    }

    public static void AfficheIntervention(Intervention i) {
        System.out.println();
        System.out.println("Type : "+i.getType());
        System.out.println("Client : "+i.getClient().getPrenom()+" "+i.getClient().getNom());
        System.out.println("Description : "+i.getDescription());
        System.out.println("Date : "+i.getDateIntervention());
        System.out.println();
        
        
    }

    public static void ConsulterIntervention(Employe e) {
        if (!e.isDisponibilite()) {
            Intervention i = Service.InterventionEnCours(e);
            AfficheIntervention(i);
            ArrayList<Integer> list = new ArrayList<Integer>();
            list.add(0);
            list.add(1);
            System.out.println("<0> : Oui - <1> : Non");
            Integer choix = Saisie.lireInteger("Voulez-vous cloturer l'intervention ?", list);
            switch (choix) {
                case 0:
                    System.out.println("<0> : Echec - <1> : Succès");
                    Integer choix2 = Saisie.lireInteger("Etat de l'intervention", list);
                    Integer heure = -1;
                    Integer minute = -1;
                    while (heure < 0 || heure > 23) {
                        heure = Saisie.lireInteger("Heure de fin entre 0 et 23 : ");
                    }
                    while (minute < 0 || minute > 59) {
                        minute = Saisie.lireInteger("Heure de fin entre 0 et 59 : ");
                    }
                    String commentaire = Saisie.lireChaine("Votre commentaire : ");
                    Service.cloturerIntervention(e, i, choix2, commentaire, heure, minute);
                    Service.NotificationClient(i);
                default:
                    break;
            }
        } else {
            System.out.println("Vous n'avez aucune intervention en cours");
        }
    }

    public static void ListerInterventionJour() {
        List<Intervention> resultats = Service.listerInterventionsJour(new Date());
        if (resultats.isEmpty()) {
            System.out.println("Aucune intervention aujourd'hui");
        } else {
            for (Intervention i : resultats) {
                AfficheIntervention(i);
            }
        }

    }

    public static void ListerInterventionClient(Client c) {
        List<Intervention> resultats = Service.listerInterventionsClient(c);
          if (resultats.isEmpty()) {
            System.out.println("Aucune intervention enregistrée");
        } else {
            for (Intervention i : resultats) {
                AfficheIntervention(i);
            }
        }
    }
    

    public static boolean ChoisirOptionClient(Client c) {
        System.out.println("Bonjour "+c.getPrenom());
        System.out.println("<0> : DÃ©connexion");
        System.out.println("<1> : Ajouter un client");
        System.out.println("<2> : Demander un intervention");
        System.out.println("<3> : Afficher votre historique d'intervention");
        System.out.println("<4> : Afficher les clients");
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Integer choix = Saisie.lireInteger("Votre choix", list);
        switch (choix) {
            case 0:
                System.out.println("Bye bye");
                return false;
            case 1:
                InscrireClient();
                return true;
            case 2:
                DemanderIntervention(c);
                return true;
            case 3:
                ListerInterventionClient(c);
                return true;
            default:
                ListerClient();
                return true;
        }
    }

    public static boolean ChoisirOptionEmploye(Employe e) {
        System.out.println("<0> : DÃ©connexion");
        System.out.println("<1> : Inscrire un client");
        System.out.println("<2> : Consulter Intervention en cours");
        System.out.println("<3> : Consulter interventions du jour");
        System.out.println("<4> : Afficher les clients");
        ArrayList<Integer> list = new ArrayList<Integer>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        Integer choix = Saisie.lireInteger("Votre choix :", list);
        switch (choix) {
            case 0:
                System.out.println("Bye bye");
                return false;
            case 1:
                InscrireClient();
                return true;
            case 2:
                ConsulterIntervention(e);
                return true;
            case 3:
                ListerInterventionJour();
                return true;
            default:
                ListerClient();
                return true;
        }
    }

    public static void Init() {
        Service.InitEmployes();
    }

}
