package modeleDemande;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleDemande {

	private Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleDemande() {
		try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE","system","prosper 54");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Probleme de pilote base de données");
			System.out.println(ex.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println("Probleme de connexion ou de requetes");
			System.out.println(ex.getMessage());
		}
	}
	
	public ArrayList<Demande> listeDemandeID(){
		ArrayList<Demande> listedemande = new ArrayList<Demande>();
			try
			{
				statement = conn.prepareStatement("SELECT DEMANDEID FROM DEMANDE");
				resultat = statement.executeQuery();
				while(resultat.next())
				{
					Demande demande = new Demande();
					demande.setDemandeID(resultat.getInt("DEMANDEID"));					
					listedemande.add(demande);	
				}
				statement.close();	
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage()+"envoyer");
			}
			return listedemande;	
		}

	public ArrayList<Demande> listeDemandeRecusTraiter()
		{
			ArrayList<Demande> listedemanderecustraiter = new ArrayList<Demande>();
				try
				{
			statement = conn.prepareStatement("SELECT * FROM DEMANDE,STATUT,UTILISATEUR,SERVICE_AGENCE WHERE SERVICE_AGENCE.SRVC_AGCID = UTILISATEUR.SRVC_AGCID AND STATUT.STATUTID = DEMANDE.STATUTID AND  UTILISATEUR.USERID = DEMANDE.USERID AND (STATUT.STATUTID=3 OR STATUT.STATUTID=4 OR STATUT.STATUTID=5)");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Demande demande = new Demande();
						demande.setDemandeID(resultat.getInt("DEMANDEID"));
						demande.setStatutID(resultat.getInt("STATUTID"));
						demande.setNumDemande(resultat.getString("NUMDEMANDE"));
						demande.setUserID(resultat.getInt("USERID"));
						demande.setStatut(resultat.getString("LIBELLESTATUT"));
						demande.setUser(resultat.getString("NOMUSER").toUpperCase());
						demande.setUserPrenom(resultat.getString("PRENOMUSER"));
						demande.setService(resultat.getString("NOMSRVC_AGC"));
						demande.setDateDemande(resultat.getString("DATEDEMANDE"));
						demande.setServiceID(resultat.getInt("SRVC_DMDID"));
						demande.setServiceUserID(resultat.getInt("SRVC_AGCID"));
						listedemanderecustraiter.add(demande);
					}
					statement.close();
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listedemanderecustraiter;
		}
		
			public ArrayList<Demande> listeDemandeEffectuee(){
			ArrayList<Demande> listedemande = new ArrayList<Demande>();
				try
				{
					statement = conn.prepareStatement("SELECT * FROM DEMANDE,STATUT,UTILISATEUR,SERVICE_AGENCE WHERE SERVICE_AGENCE.SRVC_AGCID = DEMANDE.SRVC_DMDID AND STATUT.STATUTID = DEMANDE.STATUTID AND  UTILISATEUR.USERID = DEMANDE.USERID ORDER BY DEMANDE.STATUTID ASC");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Demande demande = new Demande();
						demande.setDemandeID(resultat.getInt("DEMANDEID"));
						demande.setStatutID(resultat.getInt("STATUTID"));
						demande.setNumDemande(resultat.getString("NUMDEMANDE"));
						demande.setUserID(resultat.getInt("USERID"));
						demande.setStatutID(resultat.getInt("STATUTID"));
						demande.setStatut(resultat.getString("LIBELLESTATUT"));
						demande.setUser(resultat.getString("NOMUSER").toUpperCase());	
						demande.setUserPrenom(resultat.getString("PRENOMUSER"));
						demande.setService(resultat.getString("NOMSRVC_AGC"));			
						demande.setDateDemande(resultat.getString("DATEDEMANDE"));
						demande.setDateGraphe(resultat.getInt("X"));
						demande.setServiceID(resultat.getInt("SRVC_DMDID"));
						demande.setServiceUserID(resultat.getInt("SRVC_AGCID"));
			
						listedemande.add(demande);	
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listedemande;	
			}
			//end non valider
		public void AjoutDemande(Demande demande)
		{
			try
			{
				statement = conn.prepareStatement("INSERT INTO DEMANDE(STATUTID,NUMDEMANDE,USERID,DATEDEMANDE,SRVC_DMDID,X) VALUES(?,?,?,?,?,?)");
				//statement.setInt(1, demande.getDemandeID());
				statement.setInt(1, demande.getStatutID());
				statement.setString(2, demande.getNumDemande());
				statement.setInt(3, demande.getUserID());
				statement.setString(4, demande.getDateDemande());
				statement.setInt(5, demande.getServiceID());
				statement.setInt(6, demande.getDateGraphe());
				
				//System.out.println(demande.getDemandeID());
				System.out.println(demande.getStatutID());
				System.out.println(demande.getNumDemande());
				System.out.println(demande.getUserID());
				System.out.println(demande.getDateDemande());
				System.out.println(demande.getServiceID());
		
				statement.executeUpdate();
				System.out.println("inserer avec succes");
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage()+"ajout");
			}
		}
		
		public Demande rechercheDemande(int iddemande)
		{
			Demande demande = null;
			try
			{
				statement=conn.prepareStatement("SELECT * FROM DEMANDE WHERE DEMANDEID = ?");
				
				statement.setInt(1,iddemande);
				
				resultat = statement.executeQuery();
				
				while(resultat.next())
				{
					demande = new Demande();
					demande.setDemandeID(resultat.getInt("DEMANDEID"));
					demande.setStatutID(resultat.getInt("STATUTID"));
					demande.setNumDemande(resultat.getString("NUMDEMANDE"));
					demande.setUserID(resultat.getInt("USERID"));
					demande.setDateGraphe(resultat.getInt("X"));
					demande.setDateDemande(resultat.getString("DATEDEMANDE"));
					demande.setServiceID(resultat.getInt("SRVC_DMDID"));
				}
				statement.close();	
			}
			catch(SQLException ex)
			{
				
				System.out.println("recherche " +ex.getMessage());
			}
			return demande;
		}
		
		public void updateDemande(Demande demande)
		{
			try
			{
				statement = conn.prepareStatement("UPDATE DEMANDE SET STATUTID = ?, NUMDEMANDE = ?, USERID = ?, DATEDEMANDE = ?, SRVC_DMDID = ?, X = ? WHERE DEMANDEID = ?");
			
				statement.setInt(1, demande.getStatutID());
				statement.setString(2, demande.getNumDemande());
				statement.setInt(3, demande.getUserID());	
				statement.setString(4, demande.getDateDemande());
				statement.setInt(5, demande.getServiceID());
				statement.setInt(6, demande.getDateGraphe());
				statement.setInt(7, demande.getDemandeID());
				
				
				statement.executeUpdate();
				System.out.println("Update avec succes");
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}	
		}
		
		public void deleteDemande(int iddemande)
		{
			try
			{				
				statement = conn.prepareStatement("DELETE FROM DEMANDE WHERE DEMANDEID = ?");
				
				statement.setInt(1, iddemande);
				
				statement.executeUpdate();
				
				System.out.println("suppression avec succes");
			}
			catch(SQLException ex)
			{
				System.out.println("suppression " +ex.getMessage()+" "+ iddemande);
			}	
		}
		
		public ArrayList<Demande> recevoirDemande(){
			ArrayList<Demande> listedemandetout = new ArrayList<Demande>();
				try
				{
					statement = conn.prepareStatement("SELECT NOMSRVC_AGC,NUMDEMANDE,PRENOMUSER,DATEDEMANDE,DEMANDEID,DEMANDE.SRVC_DMDID,STATUTID,UTILISATEUR.USERID,NOMUSER,POSTEUSER,UTILISATEUR.SRVC_AGCID FROM SERVICE_AGENCE,DEMANDE,UTILISATEUR WHERE UTILISATEUR.SRVC_AGCID = SERVICE_AGENCE.SRVC_AGCID AND UTILISATEUR.USERID = DEMANDE.USERID");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Demande demande = new Demande();
						demande.setDemandeID(resultat.getInt("DEMANDEID"));
						demande.setServiceID(resultat.getInt("SRVC_DMDID"));
						demande.setStatutID(resultat.getInt("STATUTID"));
						demande.setUserID(resultat.getInt("USERID"));
						demande.setUser(resultat.getString("NOMUSER"));	
						demande.setPosteUser(resultat.getString("POSTEUSER"));
						demande.setServiceUserID(resultat.getInt("SRVC_AGCID"));		
						demande.setNumDemande(resultat.getString("NUMDEMANDE"));							
						demande.setUserPrenom(resultat.getString("PRENOMUSER"));
						demande.setService(resultat.getString("NOMSRVC_AGC"));			
						demande.setDateDemande(resultat.getString("DATEDEMANDE"));
						
						
						
						listedemandetout.add(demande);
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					System.out.println("recherche"+ex.getMessage());
				}
				return listedemandetout;	
			}
}
