/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import static modele.service.Service.Authentification;

/**
 *
 * @author hcann
 */
public class actionInscrire extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        
        String login = request.getParameter("login");
        String password = (String)request.getParameter("password");
        String result = "false";
        if(Authentification(login,password)){
            result="true";
        }
        
        request.setAttribute("connected", result);
    
        return true;
    }
    
}