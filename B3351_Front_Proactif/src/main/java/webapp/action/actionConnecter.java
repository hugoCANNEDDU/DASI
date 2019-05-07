/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import webapp.action.Action;
import javax.servlet.http.HttpServletRequest;
import static modele.service.Service.Authentification;


/**
 *
 * @author hcann
 */
public class actionConnecter extends Action{
    @Override
    public boolean executer(HttpServletRequest request){
        
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
