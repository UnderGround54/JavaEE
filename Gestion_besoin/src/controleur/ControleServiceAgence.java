package controleur;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeleDemande.Demande;
import modeleDemande.ModeleDemande;
import modeleDirection.Direction;
import modeleDirection.ModeleDirection;
import modeleServiceAgence.ModeleServiceAgence;
import modeleServiceAgence.ServiceAgence;


@WebServlet("/ControleServiceAgence")
public class ControleServiceAgence extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<ServiceAgence> listeservice=null;
	private ArrayList<Direction> listedirection = null;
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleServiceAgence donnee = new ModeleServiceAgence();
	ModeleDemande dmd = new ModeleDemande(); 
	
    public ControleServiceAgence() {
        super();
       
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		ModeleDirection direction = new ModeleDirection();
		
		listeservice = donnee.listeServiceAgence();
		listedirection = direction.listeDirection();
		
		if(path.equals("/listeServiceAgence")) {
			
			if(listedirection != null || listeservice != null) {
				request.setAttribute("listedirection",listedirection);
				request.setAttribute("listeservice",listeservice);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/Service/listeServiceAgence.jsp").forward(request,response);
		}
		
		if(lien.equals("supprservice"))
		{

			String id = request.getParameter("serviceid");
			donnee.deleteService(Integer.parseInt(id));
			
			response.sendRedirect("listeServiceAgence");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		
		ServiceAgence service = new ServiceAgence();
	    
		String directionID = request.getParameter("directionid");
		String nomService = request.getParameter("nomservice");
		String abreviationService = request.getParameter("abreviationservice");
		String typeService = request.getParameter("typeservice");
		String lieuService = request.getParameter("lieuservice");
		String codeService = request.getParameter("codeservice");
			
		service.setDirectionID(Integer.parseInt(directionID));
		service.setNomService(nomService);
		service.setAbreviationService(abreviationService);
		service.setTypeService(typeService);
		service.setLieuService(lieuService);
		service.setCodeService(codeService);
		
		if(ajout.equals("addservice"))
		{
			donnee.AjoutService(service);
			
			response.sendRedirect("listeServiceAgence");
		}
		
		if(ajout.equals("editservice"))
		{
			String serviceID = request.getParameter("serviceid");
			service.setServiceID(Integer.parseInt(serviceID));
			donnee.updateService(service);
			
			response.sendRedirect("listeServiceAgence");
		}
		
	}

}
