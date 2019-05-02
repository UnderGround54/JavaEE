package controleur;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import modeleArticle.Article;
import modeleArticle.ModeleArticle;
import modeleDemande.Demande;
import modeleDemande.ModeleDemande;
import modeleLigneDemande.LigneDemande;
import modeleLigneDemande.ModeleLigneDemande;
import modeleLigneTransfert.LigneTransfert;
import modeleLigneTransfert.ModeleLigneTransfert;
import modeleTransfert.ModeleTransfert;
import modeleTransfert.Transfert;

@WebServlet("/ControleTransfert")
public class ControleTransfert extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ArrayList<Demande> listedemanderecustraiter = null;
	private ArrayList<Transfert> listetransfert = null;	
	private ArrayList<Transfert> listedemandetransfert = null;
	
	private ArrayList<LigneTransfert> listelignetransfert = null;
	private ArrayList<Article> listearticle = null;
	private ArrayList<LigneDemande> listelignedemande = null;
	
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleDemande dmd = new ModeleDemande();
	ModeleTransfert donnee = new ModeleTransfert();
	
    public ControleTransfert() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		ModeleArticle articles = new ModeleArticle();
		ModeleLigneDemande lignedemande = new ModeleLigneDemande();
		ModeleLigneTransfert ligneTransfert = new ModeleLigneTransfert();
		
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		if(path.equals("/listeTransfertTraite") || path.equals("/listeTransfertRecu")) {

		listetransfert = donnee.listeTransfert();
		listedemandetransfert = donnee.listeDemandeTransfert();
		listearticle = articles.listeArticle();
		listelignedemande = lignedemande.listeLigneDemande();
		listelignetransfert = ligneTransfert.listeLigneTransfert();
		
		if(listearticle != null || listetransfert != null || listedemandetransfert != null
		|| listedemanderecustraiter != null || listelignedemande != null  || listelignetransfert != null) {
			request.setAttribute("listetransfert",listetransfert);
			request.setAttribute("listedemandetransfert",listedemandetransfert);
			request.setAttribute("listedemanderecustraiter",listedemanderecustraiter);
			request.setAttribute("listearticle",listearticle);
			request.setAttribute("listelignedemande",listelignedemande);
			request.setAttribute("listelignetransfert",listelignetransfert);
			}		
		
			this.getServletContext().getRequestDispatcher("/WEB-INF/Transfert/listeTransfert.jsp").forward(request,response);
		}
		
		if(lien.equals("supprtransfert"))
		{
			String id = request.getParameter("transfertid");
			donnee.deleteTransfert(Integer.parseInt(id));
			response.sendRedirect("listeTransfertTraite");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		
		Transfert transfert = new Transfert();
		ModeleDemande demandes = new ModeleDemande();
		Demande demande = new Demande();
		Date datejour = new Date();
	    
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		String datetransfert = sdf.format(datejour);
		
		String userID =(request.getParameter("userid")!=null)?request.getParameter("userid"):"";
		String statutID =(request.getParameter("statutid")!=null)?request.getParameter("statutid"):"";		
		String serviceID =(request.getParameter("serviceid")!=null)?request.getParameter("serviceid"):"";
		String numTransfert =(request.getParameter("numtransfert")!=null)?request.getParameter("numtransfert"):"";	
		String demandeID =(request.getParameter("demandeid")!=null)?request.getParameter("demandeid"):"";
		
		if(ajout.equals("addtransfert"))
		{			
			transfert.setStatutID(Integer.parseInt(statutID));
			transfert.setUserID(Integer.parseInt(userID));
			transfert.setServiceID(Integer.parseInt(serviceID));
			transfert.setDateTransfert(datetransfert);
			transfert.setNumTransfert(numTransfert);
			transfert.setDemandeID(Integer.parseInt(demandeID));		
			donnee.AjoutTransfert(transfert);
			
			
			demande = demandes.rechercheDemande(Integer.parseInt(demandeID));
			demande.setStatutID(demande.getStatutID()+1);
			demandes.updateDemande(demande);
			
			response.sendRedirect("listeDemandeRecue");
		}
		
		if(ajout.equals("edittransfert"))
		{
			String transfertID = request.getParameter("transfertid");
			
			transfert.setStatutID(Integer.parseInt(statutID));
			transfert.setUserID(Integer.parseInt(userID));
			transfert.setServiceID(Integer.parseInt(serviceID));
			transfert.setDateTransfert(datetransfert);
			transfert.setNumTransfert(numTransfert);
			transfert.setDemandeID(Integer.parseInt(demandeID));
			transfert.setTransfertID(Integer.parseInt(transfertID));
			donnee.updateTransfert(transfert);
			
			response.sendRedirect("listeTransfertTraite");
		}
			
		if(ajout.equals("editstatut"))
		{
			String transfertID = request.getParameter("transfertid");
			
				transfert = donnee.rechercheTransfert(Integer.parseInt(transfertID));
				transfert.setStatutID(transfert.getStatutID()+1);
				donnee.updateTransfert(transfert);			
				
				demande = demandes.rechercheDemande(Integer.parseInt(demandeID));
				demande.setStatutID(demande.getStatutID()+1);
				demandes.updateDemande(demande);
				
				response.sendRedirect("listeTransfertTraite");
		}
		
	}

}
