/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Client;
import static modele.service.Service.Authentification;
import static modele.service.Service.ajouterClient;

/**
 *
 * @author hcann
 */
public class actionInscrire extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        
        String email = request.getParameter("login");
        String mdp = (String)request.getParameter("password");
        String civil = (String)request.getParameter("civil");
        String nom = (String)request.getParameter("nom");
        String prenom = (String)request.getParameter("prenom");
        String dateString = (String)request.getParameter("date");
        String adresse = (String)request.getParameter("adresse");
        String tel = (String)request.getParameter("tel");
        
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try{
            date = sdf.parse(dateString);
        }catch(Exception e){
        }
       
        Client c = new Client(nom,prenom,civil,date,adresse,0,0,email,mdp,tel);
        ajouterClient(c);
       
        Action connection = new actionConnecter();
        connection.executer(request,session);
    
        return true;
    }
    
}