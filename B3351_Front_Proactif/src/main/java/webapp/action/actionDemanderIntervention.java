/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Client;
import modele.metier.Intervention;
import modele.service.Service;

/**
 *
 * @author hcann
 */
public class actionDemanderIntervention extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        String typeIntervention = (String) request.getParameter("typeIntervention");
        Client user = Service.trouverClientParMail((String) session.getAttribute("login"));
        String description = (String)request.getParameter("description");
        Intervention i = null;
        
        if(typeIntervention.equals("animal")){
            String nomAnimal = request.getParameter("nomAnimal");
            i=Service.creerInterventionAnimal(user, nomAnimal, description);
        }else if(typeIntervention.equals("incident")){
            i=Service.creerInterventionIncident(user, description);
        }else if(typeIntervention.equals("livraison")){
            String typeObjet = request.getParameter("typeObjet");
            String entreprise = request.getParameter("entreprise");

            i=Service.creerInterventionLivraison(user, typeObjet, entreprise, description);

        }
        
        if(i==null){
            request.setAttribute("success", "false");
        }else{
            request.setAttribute("success", "true");
        }
        
        return true;
    }
    
}
