package webapp.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
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
public class retourListeInterventionsSimple extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject jsonContainer=new JsonObject();
        
        List<Intervention> interventions = (List<Intervention>) request.getAttribute("result");
        
        JsonArray jsonArrayInterventions=new JsonArray();
        
        for(Intervention intervention : interventions){
            JsonObject jsonIntervention = new JsonObject();
            
            Date d = intervention.getDateIntervention();
            DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateString = sdf.format(d);
            jsonIntervention.addProperty("date", dateString);
            String type = intervention.getType();
            jsonIntervention.addProperty("type", type);
           
            jsonArrayInterventions.add(jsonIntervention);
        }
        
        jsonContainer.add("intervention",jsonArrayInterventions);
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
}
