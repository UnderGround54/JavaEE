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
import modeleDemande.Demande;
import modeleDemande.ModeleDemande;
import modeleLigneTransfert.LigneTransfert;
import modeleLigneTransfert.ModeleLigneTransfert;
import modeleTransfert.ModeleTransfert;
import modeleTransfert.Transfert;


@WebServlet("/ControleLigneTransfert")
public class ControleLigneTransfert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<LigneTransfert> listelignetransfert = null;
	private ArrayList<Transfert> listetransfert = null;
	private ArrayList<Article> listearticle = null;
	private ArrayList<Demande> listedemandemess = null;
	ModeleDemande dmd = new ModeleDemande();   
    
    public ControleLigneTransfert() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		ModeleLigneTransfert donnee = new ModeleLigneTransfert();
		ModeleTransfert transferts = new ModeleTransfert();
		ModeleArticle articles = new ModeleArticle();
		
		listelignetransfert = donnee.listeLigneTransfert();
		listetransfert = transferts.listeTransfert();
		listearticle = articles.listeArticle();
		
		if(listetransfert != null || listearticle != null || listelignetransfert != null) {
			request.setAttribute("listelignetransfert",listelignetransfert);
			request.setAttribute("listetransfert",listetransfert);
			request.setAttribute("listearticle",listearticle);
		}
		
		if(path.equals("/listeLigneTransfert")) {
			this.getServletContext().getRequestDispatcher("/WEB-INF/LigneTransfert/listeLigneTransfert.jsp").forward(request,response);
		}
		
		if(lien.equals("supprlignetransfert"))
		{
			String id = request.getParameter("lignetransfertid");
			String transfertID = request.getParameter("transfertid");
			String source = request.getParameter("source");
			
			donnee.deleteLigneTransfert(Integer.parseInt(id));
			response.sendRedirect("ControleLigneTransfert?resultat=listelignetransfert&transfertid="+transfertID+"&source="+source);
		}
		
		if(lien.equals("listelignetransfert"))
		{
			String id = request.getParameter("transfertid");
			String source = request.getParameter("source");
			
			listelignetransfert = donnee.listeDetailTrans(Integer.parseInt(id));
			Transfert transfert = transferts.rechercheTransfert(Integer.parseInt(id));
			
			request.setAttribute("statuttransfert",transfert.getStatutID());
			request.setAttribute("listelignetransfert",listelignetransfert);
			request.setAttribute("retour",source);
			this.getServletContext().getRequestDispatcher("/WEB-INF/LigneTransfert/listeLigneTransfert.jsp").forward(request,response);
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		ModeleLigneTransfert donnee = new ModeleLigneTransfert();
		LigneTransfert lignetransfert = new LigneTransfert();
		String ajout = request.getParameter("ajout");
		
		ModeleTransfert transferts = new ModeleTransfert();
		ModeleArticle articles = new ModeleArticle();
		
		listetransfert = transferts.listeTransfert();
		listearticle = articles.listeArticle();
		
		if(listetransfert != null || listearticle != null) {
			request.setAttribute("listetransfert",listetransfert);
			request.setAttribute("listearticle",listearticle);
		}
	    
		
		String categorieID = request.getParameter("articleid");
		String transfertID = request.getParameter("transfertid");
		String qteArticleTransfert = request.getParameter("qtearticletransfert");
		String commentaireTransfert = request.getParameter("commentairetransfert");
		
		lignetransfert.setArticleID(Integer.parseInt(categorieID));
		lignetransfert.setTransfertID(Integer.parseInt(transfertID));
		lignetransfert.setQteArticleTransfert(Integer.parseInt(qteArticleTransfert));
		lignetransfert.setCommentaireTransfert(commentaireTransfert);	
		
		if(ajout.equals("addlignetransfert"))
		{
			int resteStock = 0;
			Article art = articles.rechercheArticle(Integer.parseInt(categorieID));
			resteStock = art.getStock()-Integer.parseInt(qteArticleTransfert);
			if(resteStock >= 0)
			{
				art.setStock(resteStock);
				articles.updateArticle(art);
				
				donnee.AjoutLigneTransfert(lignetransfert);
				response.sendRedirect("ControleLigneTransfert?resultat=listelignetransfert&transfertid="+transfertID+"&source=trsftraite");
				
			}else{
				response.sendRedirect("listeTransfertTraite");
			}
		}
		
		if(ajout.equals("editlignetransfert"))
		{
			String lignetransfertID = request.getParameter("lignetransfertid");
			String source = request.getParameter("source");
			lignetransfert.setLigneTransfertID(Integer.parseInt(lignetransfertID));
			
			donnee.updateLigneTransfert(lignetransfert);
			response.sendRedirect("ControleLigneTransfert?resultat=listelignetransfert&transfertid="+transfertID+"&source="+source);
		}		
	}
}
