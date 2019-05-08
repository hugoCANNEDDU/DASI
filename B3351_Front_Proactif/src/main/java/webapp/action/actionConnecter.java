/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Personne;
import static modele.service.Service.Authentification;
import static modele.service.Service.trouverClientParMail;
import static modele.service.Service.trouverEmployeParMail;


/**
 *
 * @author hcann
 */
public class actionConnecter extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        
        String login = request.getParameter("login");
        String password = (String)request.getParameter("password");
        String type="null";
        if(Authentification(login,password)){
            Personne connectedUser  = null;
            connectedUser = trouverClientParMail(login);
            if(connectedUser == null){
                connectedUser = trouverEmployeParMail(login);
            }
            if(connectedUser.isEmploye()){
                type="employe";
            }else{
                type="client";
            }
            session.setAttribute("login", login);
            session.setAttribute("type",type);
        }
       
        request.setAttribute("type", type);
    
        return true;
    }
    
}
