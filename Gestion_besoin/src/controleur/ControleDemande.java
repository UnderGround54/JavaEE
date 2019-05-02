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

import modeleCategorie.Categorie;
import modeleCategorie.ModeleCategorie;
import modeleDemande.Demande;
import modeleDemande.ModeleDemande;
import modeleLigneDemande.LigneDemande;
import modeleLigneDemande.ModeleLigneDemande;
import modeleServiceAgence.ModeleServiceAgence;
import modeleServiceAgence.ServiceAgence;


@WebServlet("/ControleDemande")
public class ControleDemande extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Demande> listedemande_effectuee = null;
	private ArrayList<Demande> listedemanderecustraiter = null;
	private ArrayList<Demande> lastdemande = null;
	private ArrayList<Demande> listedemandemess = null;
	
	private ArrayList<ServiceAgence> listeservice = null;
	private ArrayList<Categorie> listecategorie = null;
	private ArrayList<LigneDemande> listelignedemande = null;
	
	ModeleDemande donnee = new ModeleDemande(); 
	ModeleServiceAgence services = new ModeleServiceAgence();
	ModeleCategorie categorie = new ModeleCategorie();
	ModeleLigneDemande ligneDem = new ModeleLigneDemande();
	
	Demande demande = new Demande(); 
    
    public ControleDemande() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");
		
		listedemandemess = donnee.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		if(path.equals("/listeDemandeEffectuee") || path.equals("/listeDemandeRecue")) {
		
		listedemande_effectuee = donnee.listeDemandeEffectuee();
		listeservice = services.listeServiceAgence();
		listecategorie = categorie.listeCategorie();
		listedemanderecustraiter = donnee.listeDemandeRecusTraiter();
		listelignedemande = ligneDem.listeLigneDemande();
		
		lastdemande=donnee.listeDemandeID();
		
		if(listedemanderecustraiter != null || listedemande_effectuee != null || listeservice != null || listecategorie != null) {
			request.setAttribute("listeservice",listeservice);
			request.setAttribute("listecategorie",listecategorie);
			request.setAttribute("listedemande_effectuee",listedemande_effectuee);
			request.setAttribute("listedemanderecustraiter",listedemanderecustraiter);
			request.setAttribute("listelignedemande",listelignedemande);
			request.setAttribute("lastdemande",lastdemande);
		}

			this.getServletContext().getRequestDispatcher("/WEB-INF/Demande/listeDemande.jsp").forward(request,response);
		}
		
		if(lien.equals("supprdemande"))
		{
			String id = request.getParameter("demandeid");
			donnee.deleteDemande(Integer.parseInt(id));
			response.sendRedirect("listeDemandeEffectuee");
		}
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		
		String userID =(request.getParameter("userid")!=null)?request.getParameter("userid"):"";
		String statutID =(request.getParameter("statutid")!=null)?request.getParameter("statutid"):"";
		
		String pattern =(request.getParameter("datedemande")!=null)?request.getParameter("datedemande"):"2019-01-01";
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
		String dateDemande = simpleDateFormat.format(new Date());
		
		String serviceID =(request.getParameter("serviceid")!=null)?request.getParameter("serviceid"):"";
		String dategraphe =(request.getParameter("dategraphe")!=null)?request.getParameter("dategraphe"):"2019";
		String numDemande =(request.getParameter("numdemande")!=null)?request.getParameter("numdemande"):"";
		
		if(ajout.equals("adddemande"))
		{	
			demande.setStatutID(1);
			demande.setUserID(Integer.parseInt(userID));
			demande.setServiceID(Integer.parseInt(serviceID));
			demande.setDateDemande(dateDemande);
			demande.setNumDemande(numDemande);
			demande.setDateGraphe(Integer.parseInt(dategraphe));
			donnee.AjoutDemande(demande);
			
			response.sendRedirect("listeDemandeEffectuee");
		}
		
		if(ajout.equals("editdemande"))
		{		
			String demandeID = request.getParameter("demandeid");
			
			demande.setStatutID(Integer.parseInt(statutID));
			demande.setUserID(Integer.parseInt(userID));
			demande.setServiceID(Integer.parseInt(serviceID));
			demande.setDateDemande(dateDemande);
			demande.setNumDemande(numDemande);		
			demande.setDateGraphe(Integer.parseInt(dategraphe));		
			demande.setDemandeID(Integer.parseInt(demandeID));
			
			System.out.println(demandeID+"   "+statutID+"   "+userID+"   "+serviceID+"   "+dategraphe+"   "+dateDemande+"   "+numDemande);
			donnee.updateDemande(demande);
			
			response.sendRedirect("listeDemandeEffectuee");
		}
		
		if(ajout.equals("editstatut"))
		{			
			
			String demandeID = request.getParameter("demandeid");
			demande = donnee.rechercheDemande(Integer.parseInt(demandeID));
			demande.setStatutID(demande.getStatutID()+1);
			donnee.updateDemande(demande);

			response.sendRedirect("listeDemandeEffectuee");					
		}	
		
		
	}

}
