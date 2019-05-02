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
import modeleMateriel.Materiel;
import modeleMateriel.ModeleMateriel;


@WebServlet("/ControleMateriel")
public class ControleMateriel extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Materiel> listemateriel=null;    
	private ArrayList<Demande> listedemandemess = null;
	ModeleDemande dmd = new ModeleDemande();  
    public ControleMateriel() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		ModeleMateriel donnee = new ModeleMateriel();
		listemateriel = donnee.listeMateriel();
		
		if(path.equals("/listeMateriel")) {
			
			if(listemateriel != null) {
				request.setAttribute("listemateriel",listemateriel);
			}else{
				request.setAttribute("listemateriel","vide");
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/Materiel/listeMateriel.jsp").forward(request,response);
		}
		
		if(path.equals("/AjoutMateriel")) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Materiel/AjoutMateriel.jsp").forward(request,response);
		}
		
		if(lien.equals("modifmateriel"))
		{	
			String id = request.getParameter("materielid");
			int idmateriel = Integer.parseInt(id);
			Materiel materiel = donnee.rechercheMateriel(idmateriel);
			request.setAttribute("materiel", materiel);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Materiel/EditMateriel.jsp").forward(request,response);
		}
		
		if(lien.equals("supprmateriel"))
		{

			String id = request.getParameter("materielid");
			int idmateriel = Integer.parseInt(id);
			donnee.deleteMateriel(idmateriel);
			listemateriel = donnee.listeMateriel();
			request.setAttribute("listemateriel",listemateriel);
			this.getServletContext().getRequestDispatcher("/WEB-INF/Materiel/listeMateriel.jsp").forward(request,response);
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		
		ModeleMateriel donnee = new ModeleMateriel();
		Materiel materiel = new Materiel();
		String ajout = request.getParameter("ajout");
	    
		String materielID = request.getParameter("materielid");
		String catecorieid = request.getParameter("categorieid");
		String nommateriel = request.getParameter("nommateriel");
		String stock = request.getParameter("stock");
		String etat = request.getParameter("etat");
		String codemat = request.getParameter("codemateriel");
			
		materiel.setMaterielID(Integer.parseInt(materielID));
		materiel.setCategorieID(Integer.parseInt(catecorieid));
		materiel.setNomMateriel(nommateriel);
		materiel.setStock(Integer.parseInt(stock));
		materiel.setEtat(etat);
		materiel.setCodeMateriel(codemat);
		
		if(ajout.equals("addmateriel"))
		{
			donnee.AjoutMateriel(materiel);
			
			listemateriel = donnee.listeMateriel();
			request.setAttribute("listemateriel",listemateriel);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Materiel/listeMateriel.jsp").forward(request,response);
		}
		
		if(ajout.equals("editmateriel"))
		{
			donnee.updateMateriel(materiel);
			
			listemateriel = donnee.listeMateriel();
			request.setAttribute("listemateriel",listemateriel);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Materiel/listeMateriel.jsp").forward(request,response);
		}
		
	}
}
