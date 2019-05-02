package controleur;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeleArticle.Article;
import modeleArticle.ModeleArticle;
import modeleCategorie.Categorie;
import modeleCategorie.ModeleCategorie;
import modeleDemande.Demande;
import modeleDemande.ModeleDemande;


@WebServlet("/ControleArticle")
public class ControleArticle extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Article> listearticle=null; 
	private ArrayList<Categorie> listecategorie = null;
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleArticle donnee = new ModeleArticle();
	ModeleDemande dmd = new ModeleDemande();  
    
    public ControleArticle() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		ModeleCategorie categorie = new ModeleCategorie();
		
		listecategorie = categorie.listeCategorie();
		listearticle = donnee.listeArticle();
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		if(path.equals("/listeArticle")) {
			
			if(listecategorie != null || listearticle != null) {
				
			request.setAttribute("listecategorie",listecategorie);
			request.setAttribute("listearticle",listearticle);
			
			}
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Article/listeArticle.jsp").forward(request,response);
		}
	
		if(lien.equals("supprarticle"))
		{

			String id = request.getParameter("articleid");		
			donnee.deleteArticle(Integer.parseInt(id));
			
			response.sendRedirect("listeArticle");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		Article article = new Article();
	    
		String categorieid = request.getParameter("categorieid");
		String nomarticle = request.getParameter("nomarticle");
		String stock = request.getParameter("stock");
		String etat = request.getParameter("etat");
		String codemat = request.getParameter("codearticle");
			
		article.setCategorieID(Integer.parseInt(categorieid));
		article.setNomArticle(nomarticle);
		article.setStock(Integer.parseInt(stock));
		article.setEtat(etat);
		article.setCodeArticle(codemat);
		
		if(ajout.equals("addarticle"))
		{
			donnee.AjoutArticle(article);
			
			response.sendRedirect("listeArticle");
		}
		
		if(ajout.equals("editarticle"))
		{
			String articleID = request.getParameter("articleid");
			article.setArticleID(Integer.parseInt(articleID));
			donnee.updateArticle(article);
			
			response.sendRedirect("listeArticle");
		}
		
	}
}
