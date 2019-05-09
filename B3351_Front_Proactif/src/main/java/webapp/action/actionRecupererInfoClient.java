/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Client;
import modele.metier.Employe;
import modele.service.Service;

/**
 *
 * @author hcann
 */
public class actionRecupererInfoClient extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        String login = (String) session.getAttribute("login");
        
        Client user = Service.trouverClientParMail(login);
        
        request.setAttribute("adresse", user.getAdresse());
        request.setAttribute("mail", user.getMail());
        request.setAttribute("tel", user.getNumTel());
        request.setAttribute("nom", user.getNom());
        request.setAttribute("prenom", user.getPrenom());
        request.setAttribute("civilite", user.getCivilite());
        request.setAttribute("dateNaissance", user.getDateNaissance());
                
        return true;
    }
}
