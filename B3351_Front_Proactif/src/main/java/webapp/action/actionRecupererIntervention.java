/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Employe;
import modele.metier.Intervention;
import modele.metier.InterventionAnimal;
import modele.metier.InterventionLivraison;
import static modele.service.Service.InterventionEnCours;
import static modele.service.Service.trouverEmployeParMail;

/**
 *
 * @author hcann
 */
public class actionRecupererIntervention extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        
        String login = (String)session.getAttribute("login");
        Employe user = trouverEmployeParMail(login);
        
        Intervention i = InterventionEnCours(user);
        
        String adresse = i.getClient().getAdresse();
        String type = i.getType();
        
        request.setAttribute("description",i.getDescription());
        request.setAttribute("type",i.getType());
        request.setAttribute("adresse",adresse);
        
        if(type.equals("Livraison")){
            InterventionLivraison iL=(InterventionLivraison) i;
            request.setAttribute("typeObjet",iL.getTypeObjet());
            request.setAttribute("entreprise",iL.getEntreprise());
        }else if(type.equals("Animal")){
            InterventionAnimal iA= (InterventionAnimal) i;
            request.setAttribute("nomAnimal", iA.getNomAnimal());
        }
        return true;
    }
    
}