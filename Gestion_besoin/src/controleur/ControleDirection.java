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

@WebServlet("/ControleDirection")
public class ControleDirection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Direction> listedirection = null;
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleDirection donnee = new ModeleDirection();
	ModeleDemande dmd = new ModeleDemande();  
 
    public ControleDirection() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		listedirection = donnee.listeDirection();
		
		if(path.equals("/listeDirection")) {
			
			if(listedirection != null) {
				request.setAttribute("listedirection",listedirection);
			}
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Direction/listeDirection.jsp").forward(request,response);
		}
		
		if(lien.equals("supprdirection"))
		{

			String id = request.getParameter("directionid");
			donnee.deleteDirection(Integer.parseInt(id));
			
			response.sendRedirect("listeDirection");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		String ajout = request.getParameter("ajout");
		
		Direction direction = new Direction();
			
		String libelle = request.getParameter("libelledirection");
		String abreviationDirection = request.getParameter("abreviationdirection");
	
		
		direction.setLibelleDirection(libelle);
		direction.setAbreviationDirection(abreviationDirection);
		
		if(ajout.equals("adddirection"))
		{
			donnee.AjoutDirection(direction);
			
			response.sendRedirect("listeDirection");
		}
		
		if(ajout.equals("editdirection"))
		{
			String directionID = request.getParameter("directionid");
			direction.setDirectionID(Integer.parseInt(directionID));
			
			donnee.updateDirection(direction);
			
			response.sendRedirect("listeDirection");
		}
		
	}


}
