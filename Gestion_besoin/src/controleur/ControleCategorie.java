package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeleCategorie.Categorie;
import modeleCategorie.ModeleCategorie;
import modeleDemande.Demande;
import modeleDemande.ModeleDemande;


@WebServlet("/ControleCategorie")
public class ControleCategorie extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Categorie> listecategorie = null;
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleCategorie donnee = new ModeleCategorie();
	ModeleDemande dmd = new ModeleDemande();      
    
    public ControleCategorie() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		listecategorie = donnee.listeCategorie();
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		if(path.equals("/listeCategorie")) {		
			if(listecategorie != null) {
				request.setAttribute("listecategorie",listecategorie);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/Categorie/listeCategorie.jsp").forward(request,response);
		}
		
		if(lien.equals("supprcategorie"))
		{
			String id = request.getParameter("categorieid");
			donnee.deleteCategorie(Integer.parseInt(id));
			
			response.sendRedirect("listeCategorie");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		
		Categorie categorie = new Categorie();    
		
		String libelle = request.getParameter("libellecategorie");
		
		categorie.setLibelleCategorie(libelle);
		
		if(ajout.equals("addcategorie"))
		{
			donnee.AjoutCategorie(categorie);
			
			response.sendRedirect("listeCategorie");
		}
		
		if(ajout.equals("editcategorie"))
		{
			String categorieID = request.getParameter("categorieid");
			categorie.setCategorieID(Integer.parseInt(categorieID));
			
			donnee.updateCategorie(categorie);
			response.sendRedirect("listeCategorie");
		}
		
	}

}
