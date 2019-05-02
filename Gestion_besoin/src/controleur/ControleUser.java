package controleur;


import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modeleDemande.Demande;
import modeleDemande.ModeleDemande;
import modeleServiceAgence.ModeleServiceAgence;
import modeleServiceAgence.ServiceAgence;
import modeleUser.ModeleUser;
import modeleUser.Utilisateur;


@WebServlet("/ControleUser")
public class ControleUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ArrayList<Utilisateur> liste = null;
	private ArrayList<ServiceAgence> listeservice = null;
	private ArrayList<Demande> listedemandemess = null;
	
	ModeleUser donnee = new ModeleUser();
	ModeleDemande dmd = new ModeleDemande();
   
    public ControleUser() {
        super();
       
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String path = request.getServletPath();
		String lien = request.getParameter("resultat");

		ModeleServiceAgence service = new ModeleServiceAgence();
			
		listedemandemess = dmd.recevoirDemande();
		request.setAttribute("listedemandemess", listedemandemess);
		
		liste = donnee.listeUser();
		listeservice = service.listeServiceAgence();
		
		if(listeservice != null) {
			request.setAttribute("listeservice",listeservice);
		}else{
			request.setAttribute("listeservice","vide");
		}
	
		if(path.equals("/")) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Login/login.jsp").forward(request,response);
		}
		
		if(path.equals("/listeUtilisateur")) {
			
			if(liste!=null) {
				request.setAttribute("liste",liste);
			}
			this.getServletContext().getRequestDispatcher("/WEB-INF/Utilisateur/listeUtilisateur.jsp").forward(request,response);
		}

		if(path.equals("/index")) {
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/index.jsp").forward(request,response);
		}
		
		if(path.equals("/login")) {
			HttpSession session = request.getSession(false);
			session.invalidate();
			
			this.getServletContext().getRequestDispatcher("/WEB-INF/Login/login.jsp").forward(request,response);
		}
		
		if(lien.equals("suppr"))
		{
			String id = request.getParameter("id");
			donnee.deleteUser(Integer.parseInt(id));
			
			response.sendRedirect("listeUtilisateur");
		}
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String ajout = request.getParameter("ajout");
		
		Utilisateur user = new Utilisateur();

		String serviceID = request.getParameter("serviceid");
		String droituser = request.getParameter("droituser");
		String prenom = request.getParameter("prenom");
		String nom = request.getParameter("nom");
		String adresse = request.getParameter("adresse");
		String login = request.getParameter("login");
		String pass = request.getParameter("pass");
		String poste = request.getParameter("poste");	
		
		if(ajout.equals("add"))
		{
			
			liste = donnee.validerLogin(login, pass);
			
			if(liste.size()==0) {
				user.setServiceID(Integer.parseInt(serviceID));
				user.setDroitUser(Integer.parseInt(droituser));
				user.setPrenomUser(prenom);
				user.setNomUser(nom);
				user.setAdresseUser(adresse);
				user.setLoginUser(login);
				user.setMotDePass(pass);
				user.setPosteUser(poste);
				donnee.AjoutUser(user);
			}
			response.sendRedirect("listeUtilisateur");
		}
		
		if(ajout.equals("edit"))
		{
			user.setServiceID(Integer.parseInt(serviceID));
			user.setDroitUser(Integer.parseInt(droituser));
			user.setPrenomUser(prenom);
			user.setNomUser(nom);
			user.setAdresseUser(adresse);
			user.setLoginUser(login);
			user.setMotDePass(pass);
			user.setPosteUser(poste);
			
			String ID = request.getParameter("userid");
			user.setUserID(Integer.parseInt(ID));
			
			donnee.updateUser(user);
			response.sendRedirect("listeUtilisateur");
		}
		
		if(ajout.equals("login"))
		{
			HttpSession session = request.getSession();
			liste = donnee.validerLogin(login, pass);
			
			if (liste.size()!=0)
			{
				
				session.setAttribute("session", liste);
				response.sendRedirect("index");
			}
			else
			{
				request.setAttribute("trouve", "login ou mot de passe incorrect");
				this.getServletContext().getRequestDispatcher("/WEB-INF/Login/login.jsp").forward(request,response);
			}
		}
	}
}
