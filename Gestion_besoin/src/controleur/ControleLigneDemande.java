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
import modeleLigneDemande.LigneDemande;
import modeleLigneDemande.ModeleLigneDemande;


@WebServlet("/ControleLigneDemande")
public class ControleLigneDemande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<LigneDemande> listelignedemande = null;
	private ArrayList<Categorie> listecategorie = null;
	private ArrayList<Demande> listedemande = null;
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleDemande dmd = new ModeleDemande();    
	ModeleLigneDemande donnee = new ModeleLigneDemande();
	
    public ControleLigneDemande() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");

		ModeleCategorie categories = new ModeleCategorie();
		ModeleDemande demandes = new ModeleDemande();
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
			
		listelignedemande = donnee.listeLigneDemande();
		listecategorie = categories.listeCategorie();
		listedemande = demandes.listeDemandeEffectuee(); 
		
		if(listedemande != null || listecategorie != null || listelignedemande != null) {
			request.setAttribute("listedemande",listedemande);
			request.setAttribute("listecategorie",listecategorie);
			request.setAttribute("listelignedemande",listelignedemande);
			}
		if(path.equals("/listeLigneDemande")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/LigneDemande/listeLigneDemande.jsp").forward(request,response);
		}
		
		if(lien.equals("supprlignedemande"))
		{
			String id = request.getParameter("lignedemandeid");
			String demandeID = request.getParameter("demandeid");
			String source = request.getParameter("source");
			
			donnee.deleteLigneDemande(Integer.parseInt(id));		
			response.sendRedirect("ControleLigneDemande?resultat=listelignedemande&demandeid="+demandeID+"&source="+source);
		}
		
		if(lien.equals("listelignedemande"))
		{
			String id = request.getParameter("demandeid");
			String source = request.getParameter("source"); 
			
			listelignedemande = donnee.listeDetail(Integer.parseInt(id));
			Demande demande = demandes.rechercheDemande(Integer.parseInt(id));
			
			request.setAttribute("statutdemande",demande.getStatutID());
			request.setAttribute("listelignedemande",listelignedemande);
			request.setAttribute("retour",source);
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/LigneDemande/listeLigneDemande.jsp").forward(request,response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		
		LigneDemande lignedemande = new LigneDemande();
			
		String categorieID = request.getParameter("categorieid");
		String demandeID = request.getParameter("demandeid");
		String qteArticleDemande = request.getParameter("qtearticledemande");
		String commentaireDemande = request.getParameter("commentairedemande");	
		
		lignedemande.setCategorieID(Integer.parseInt(categorieID));
		lignedemande.setDemandeID(Integer.parseInt(demandeID));
		lignedemande.setQteArticleDemande(Integer.parseInt(qteArticleDemande));
		lignedemande.setCommentaireDemande(commentaireDemande);
		
		if(ajout.equals("addlignedemande"))
		{
			donnee.AjoutLigneDemande(lignedemande);			
			response.sendRedirect("ControleLigneDemande?resultat=listelignedemande&demandeid="+demandeID+"&source=dmdeff");
		}
		
		if(ajout.equals("editlignedemande"))
		{
			String lignedemandeID = request.getParameter("lignedemandeid");
			String source = request.getParameter("source");
			lignedemande.setLigneDemandeID(Integer.parseInt(lignedemandeID));
			
			donnee.updateLigneDemande(lignedemande);
			response.sendRedirect("ControleLigneDemande?resultat=listelignedemande&demandeid="+demandeID+"&source="+source);
		}
		
	}

}
