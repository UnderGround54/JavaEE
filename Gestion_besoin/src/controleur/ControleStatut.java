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
import modeleStatut.ModeleStatut;
import modeleStatut.Statut;

/**
 * Servlet implementation class ControleStatut
 */
@WebServlet("/ControleStatut")
public class ControleStatut extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Statut> listestatut = null;
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleStatut donnee = new ModeleStatut();
	ModeleDemande dmd = new ModeleDemande();  
	
    public ControleStatut() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");

		listestatut = donnee.listeStatut();
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		if(path.equals("/listeStatut")) {
			
			if(listestatut != null) {
				request.setAttribute("listestatut",listestatut);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/Statut/listeStatut.jsp").forward(request,response);
		}

		if(lien.equals("supprstatut"))
		{
			String id = request.getParameter("statutid");
			donnee.deleteStatut(Integer.parseInt(id));
			response.sendRedirect("listeStatut");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		
		Statut statut = new Statut();
	
		String libelle = request.getParameter("libellestatut");
	
		statut.setLibelleStatut(libelle);
		
		if(ajout.equals("addstatut"))
		{
			donnee.AjoutStatut(statut);
			
			response.sendRedirect("listeStatut");
			
		}
		
		if(ajout.equals("editstatut"))
		{
			String statutID = request.getParameter("statutid");
			statut.setStatutID(Integer.parseInt(statutID));
			donnee.updateStatut(statut);
			response.sendRedirect("listeStatut");
		}
		
	}

}
