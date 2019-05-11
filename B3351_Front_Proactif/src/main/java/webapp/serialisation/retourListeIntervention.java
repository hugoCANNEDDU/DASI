/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modele.metier.Intervention;
import modele.metier.InterventionAnimal;
import modele.metier.InterventionLivraison;

/**
 *
 * @author hcann
 */
public class retourListeIntervention extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject jsonContainer=new JsonObject();
        
        List<Intervention> interventions = (List<Intervention>) request.getAttribute("result");
        
        JsonArray jsonArrayInterventions=new JsonArray();
        
        for(Intervention intervention : interventions){
            JsonObject jsonIntervention = new JsonObject();
            
            jsonIntervention.addProperty("statut", intervention.getStatut());
            jsonIntervention.addProperty("commentaire", intervention.getCommentaire());
            jsonIntervention.addProperty("description", intervention.getDescription());
            
            String type = intervention.getType();
            jsonIntervention.addProperty("type", type);
            
            if(type.equals("Livraison")){
                InterventionLivraison iL = (InterventionLivraison)intervention;
                jsonIntervention.addProperty("typeObjet",iL.getTypeObjet());
                jsonIntervention.addProperty("entreprise",iL.getEntreprise());
            }else if(type.equals("Animal")){
                InterventionAnimal iA = (InterventionAnimal)intervention;
                jsonIntervention.addProperty("nomAnimal",iA.getNomAnimal());
            }
            jsonArrayInterventions.add(jsonIntervention);
        }
        
        jsonContainer.add("intervention",jsonArrayInterventions);
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
}