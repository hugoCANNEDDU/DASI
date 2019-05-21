/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.action;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class actionRecupererInterventionClient extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        String dateString = (String) request.getParameter("date");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date=null;
        try{
            date = sdf.parse(dateString);
        }catch(Exception e){
        }
           
        Client user = Service.trouverClientParMail((String) session.getAttribute("login"));
        List<Intervention> result = Service.listerInterventionsClient(user);
        List<Intervention> resultSelect=new ArrayList<Intervention>();
        for(Intervention i:result){
            if(i.getDateIntervention().equals(date )){
                resultSelect.add(i);
            }
        }
        request.setAttribute("result", resultSelect);
        
        return true;
    }
    
}
