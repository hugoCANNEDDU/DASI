
package webapp.action;

import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import modele.metier.Employe;
import modele.metier.Intervention;
import modele.metier.InterventionAnimal;
import modele.metier.InterventionLivraison;
import static modele.service.Service.InterventionEnCours;
import static modele.service.Service.trouverEmployeParMail;

public class actionCloturerIntervention extends Action{
    @Override
    public boolean executer(HttpServletRequest request, HttpSession session){
        
        String login = (String)session.getAttribute("login");
        Employe user = trouverEmployeParMail(login);
        
        Intervention i = InterventionEnCours(user);
        
        String adresse = i.getClient().getAdresse();
        String type = i.getType();
        Integer heure =(Integer)request.getAttribute("heure");
        Integer minute =(Integer)request.getAttribute("minute");
        Date laDate=new Date(heure,minute,0);
        i.setHeureFin((Date)request.getAttribute((String)laDate));
        String stat=(String)request.getAttribute("statut");
        Integer s=0;
        if(stat.equals("reussite")){
            i.setStatut("true");
            s=1;
        }else{
            i.setStatut("false");
        }
        i.setCommentaire((String)request.getAttribute("commentaire"));
        
        user.isDisponibilite();
        
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
        
        cloturerIntervention(user,i, s, commentaire, Integer h, Integer m)
        return true;
    }

   
    
}

