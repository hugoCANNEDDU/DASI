/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package webapp.serialisation;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hcann
 */
public class retourIntervention extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject jsonContainer=new JsonObject();
        
        String type = (String)request.getAttribute("type");
        jsonContainer.addProperty("type",type);
        jsonContainer.addProperty("description",(String)request.getAttribute("description"));
        jsonContainer.addProperty("adresse",(String)request.getAttribute("adresse"));
        
        if(type.equals("Livraison")){
            jsonContainer.addProperty("typeObjet",(String)request.getAttribute("typeObjet"));
            jsonContainer.addProperty("entreprise",(String)request.getAttribute("entreprise"));
        }else if(type.equals("Animal")){
            jsonContainer.addProperty("nomAnimal",(String)request.getAttribute("nomAnimal"));
        }
        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
}
