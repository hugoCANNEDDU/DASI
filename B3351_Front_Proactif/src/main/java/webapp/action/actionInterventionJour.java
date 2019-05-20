/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Client;
import modele.metier.Intervention;
import modele.service.Service;

/**
 *
 * @author hcann
 */
public class actionInterventionJour extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        Client user = Service.trouverClientParMail((String) session.getAttribute("login"));
        
        List<Intervention> result = Service.listerInterventionsJour(new Date());

        request.setAttribute("result", result);
        
        return true;
    }
    
}
