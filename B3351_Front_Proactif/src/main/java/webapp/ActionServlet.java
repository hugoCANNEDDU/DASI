package webapp;



/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import dao.JpaUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import static modele.service.Service.Authentification;
import static modele.service.Service.trouverEmployeParMail;
import javax.servlet.http.HttpSession;
import modele.metier.Employe;
import webapp.action.Action;
import webapp.action.actionCloturerIntervention;
import webapp.action.actionConnecter;
import webapp.action.actionDemanderIntervention;
import webapp.action.actionInscrire;
import webapp.action.actionRecupererInfoClient;
import webapp.action.actionRecupererInfoEmploye;
import webapp.action.actionRecupererIntervention;
import webapp.action.actionRecupererInterventionClient;
import webapp.serialisation.Serialisation;
import webapp.serialisation.retourCloturerIntervention;
import webapp.serialisation.retourConnecter;
import webapp.serialisation.retourDemandeIntervention;
import webapp.serialisation.retourInfoClient;
import webapp.serialisation.retourInfoEmploye;
import webapp.serialisation.retourIntervention;
import webapp.serialisation.retourListeIntervention;

/**
 *
 * @author ggribbeni
 */
@WebServlet(urlPatterns = {"/ActionServlet"})
public class ActionServlet extends HttpServlet {

    
    
    @Override
    public void init()
            throws ServletException {
        JpaUtil.init();
        super.init();
        //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    public void destroy() {
        JpaUtil.destroy();
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(true);
        request.setCharacterEncoding("UTF-8");
        String todo=request.getParameter("todo");
        Action action=null;
        Serialisation serialisation=null;
        
        switch(todo){
            case "connecter":
                action = new actionConnecter();
                serialisation = new retourConnecter();
                break;
            case "inscrire":
                action = new actionInscrire();
                serialisation = new retourConnecter();
                break;
            case "recupererInfo":
                if("employe".equals((String)session.getAttribute("type"))){
                    action = new actionRecupererInfoEmploye();
                    serialisation = new retourInfoEmploye();
                }else if("client".equals((String)session.getAttribute("type"))){
                    action = new actionRecupererInfoClient();
                    serialisation = new retourInfoClient();
                }else{
                    response.sendError(401,"type non trouvé");
                }
                break;
            case "deconnecter":
                session = null;
                break;
            case "recupererIntervention":
                action = new actionRecupererIntervention();
                serialisation = new retourIntervention();
                break;
            case "demandeIntervention":
                action = new actionDemanderIntervention();
                serialisation = new retourDemandeIntervention();
                break;
            case "cloturer":
                action = new actionCloturerIntervention();
                serialisation = new retourCloturerIntervention();
                break;
            case "recupererListeIntervention":
                action = new actionRecupererInterventionClient();
                serialisation = new retourListeIntervention();
                break;   
        }
        
        if(action==null){
            response.sendError(400,"Bad Request (wrong TODO parameter)");
        }else{
            action.executer(request, session);
            serialisation.serialiser(request, response);
        }
        
        
        
        
        
        /*response.setContentType("application/json;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            HttpSession session = request.getSession(true);
            request.setCharacterEncoding("UTF-8");
            String action=request.getParameter("action");

            if("connecter".equals(action)){
                String login = request.getParameter("login");
                String password = (String)request.getParameter("password");
                if(Authentification(login,password)){
                    session.setAttribute("utilisateur", login);
                    out.println("{\"connexion\":true}");
                }else{
                    out.println("{\"connexion\":false}");
                }
            }else if("recupererLogin".equals(action)){
                Employe e=trouverEmployeParMail((String) session.getAttribute("utilisateur"));
                String email=e.getMail();
                out.println("{\"mail\":\""+email+"\"}");
            }
        }*/
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
