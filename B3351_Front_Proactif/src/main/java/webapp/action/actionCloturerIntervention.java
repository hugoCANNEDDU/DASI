
package webapp.action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Employe;
import modele.metier.Intervention;
import modele.metier.InterventionAnimal;
import modele.metier.InterventionLivraison;
import static modele.service.Service.InterventionEnCours;
import static modele.service.Service.cloturerIntervention;
import static modele.service.Service.trouverEmployeParMail;

public class actionCloturerIntervention extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        
        String login = (String)session.getAttribute("login");
        Employe user = trouverEmployeParMail(login);
        
        Intervention i = InterventionEnCours(user);
        String commentaire=(String)request.getParameter("commentaire");
        String heure =request.getParameter("heure");
        Integer h=Integer.parseInt(heure);
        String minute =request.getParameter("minute");
        Integer m=Integer.parseInt(minute);
        String stat=(String)request.getParameter("statut");
        Integer s=0;
        if(stat.equals("reussite")){
            s=1;
        }
      
        cloturerIntervention(user,i, s, commentaire, h,m);
        request.setAttribute("success","true");
        return true;
    }

   
    
}

