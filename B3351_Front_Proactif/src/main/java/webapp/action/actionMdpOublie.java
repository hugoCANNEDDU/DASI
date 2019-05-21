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
 * @author GRAZIA GIULIA
 */
public class actionMdpOublie extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        
        String login = request.getParameter("login");
        /*Personne user  = null;
        user = trouverClientParMail(login);*/
        if(trouverClientParMail(login)!=null || trouverEmployeParMail(login)!=null){
            request.setAttribute("success","true"); 
        }else{
            request.setAttribute("success","false");
        }
    
        return true;
    }
    
}


