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
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author hcann
 */
public class retourInfoClient extends Serialisation{
    @Override
    public void serialiser(HttpServletRequest request, HttpServletResponse response)throws IOException{
        JsonObject jsonContainer=new JsonObject();
        jsonContainer.addProperty("adresse",(String)request.getAttribute("adresse"));
        jsonContainer.addProperty("mail",(String)request.getAttribute("mail"));
        jsonContainer.addProperty("tel",(String)request.getAttribute("tel"));
        jsonContainer.addProperty("nom",(String)request.getAttribute("nom"));
        jsonContainer.addProperty("prenom",(String)request.getAttribute("prenom"));
        jsonContainer.addProperty("civilite",(String)request.getAttribute("civilite"));
        Date d = (Date) request.getAttribute("dateNaissance");
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd");
        String dateString = sdf.format(d);
        jsonContainer.addProperty("dateNaissance",dateString);

        
        PrintWriter out = this.getWriterWithJsonHeader(response);
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        gson.toJson(jsonContainer,out);
    }
}