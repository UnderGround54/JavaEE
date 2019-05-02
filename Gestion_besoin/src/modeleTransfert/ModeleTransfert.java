package modeleTransfert;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleTransfert {
	
		private Connection conn = null;
		private PreparedStatement statement = null;
		private ResultSet resultat = null;
		
		public ModeleTransfert() {
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
		
		public ArrayList<Transfert> listeTransfert(){
			ArrayList <Transfert> listetransfert = new ArrayList<Transfert>();
				try
				{
					statement = conn.prepareStatement("SELECT TRANSFERT.TRANSFERTID,TRANSFERT.DEMANDEID,NUMTRANSFERT,NUMDEMANDE,UTILISATEUR.SRVC_AGCID,NOMUSER,NOMSRVC_AGC,DATETRANSFERT,LIBELLESTATUT FROM TRANSFERT,STATUT,UTILISATEUR,SERVICE_AGENCE,DEMANDE WHERE STATUT.STATUTID = TRANSFERT.STATUTID AND SERVICE_AGENCE.SRVC_AGCID = TRANSFERT.SRVC_TRSFID AND UTILISATEUR.USERID = TRANSFERT.USERID AND DEMANDE.DEMANDEID = TRANSFERT.DEMANDEID ORDER BY TRANSFERT.STATUTID ASC");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Transfert transfert = new Transfert();
						transfert.setTransfertID(resultat.getInt("TRANSFERTID"));
						transfert.setNumTransfert(resultat.getString("NUMTRANSFERT"));
						transfert.setDemande(resultat.getString("NUMDEMANDE"));
						transfert.setUser(resultat.getString("NOMUSER"));
						transfert.setService(resultat.getString("NOMSRVC_AGC"));
						transfert.setDateTransfert(resultat.getString("DATETRANSFERT"));
						transfert.setStatut(resultat.getString("LIBELLESTATUT"));
						transfert.setDemandeID(resultat.getInt("DEMANDEID"));
						transfert.setServiceUserID(resultat.getInt("SRVC_AGCID"));
						
						/*transfert.setStatutID(resultat.getInt("STATUTID"));
						
						transfert.setUserID(resultat.getInt("USERID"));
						
						transfert.setServiceID(resultat.getInt("SRVC_TRSFID"));																					
						transfert.setDateTransfert(resultat.getString("DATETRANSFERT"));
						transfert.setDateDMD(resultat.getString("DATEDEMANDE"));*/
						//transfert.setServiceUserID(resultat.getInt("SRVC_AGCID"));
						listetransfert.add(transfert);	
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listetransfert;	
			}
		
		
		public ArrayList<Transfert> listeDemandeTransfert(){
			ArrayList <Transfert> listedemandetransfert = new ArrayList<Transfert>();
				try
				{
					statement = conn.prepareStatement("SELECT TRANSFERTID,NUMTRANSFERT,SRVC_TRSFID,NOMUSER,PRENOMUSER,NOMSRVC_AGC,DATETRANSFERT,LIBELLESTATUT FROM TRANSFERT,STATUT,UTILISATEUR,SERVICE_AGENCE,DEMANDE WHERE STATUT.STATUTID = TRANSFERT.STATUTID AND SERVICE_AGENCE.SRVC_AGCID = UTILISATEUR.SRVC_AGCID AND UTILISATEUR.USERID = TRANSFERT.USERID AND DEMANDE.DEMANDEID = TRANSFERT.DEMANDEID AND TRANSFERT.STATUTID = 5");
					resultat = statement.executeQuery();
					while(resultat.next())
					{
						Transfert transfert = new Transfert();
						transfert.setTransfertID(resultat.getInt("TRANSFERTID"));
						transfert.setNumTransfert(resultat.getString("NUMTRANSFERT"));
						transfert.setUser(resultat.getString("NOMUSER")+" "+resultat.getString("PRENOMUSER"));
						transfert.setService(resultat.getString("NOMSRVC_AGC"));
						transfert.setDateTransfert(resultat.getString("DATETRANSFERT"));
						transfert.setStatut(resultat.getString("LIBELLESTATUT"));
						transfert.setServiceID(resultat.getInt("SRVC_TRSFID"));
						
						/*transfert.setStatutID(resultat.getInt("STATUTID"));
						transfert.setStatut(resultat.getString("LIBELLESTATUT"));
						transfert.setUserID(resultat.getInt("USERID"));
						
						transfert.setServiceID(resultat.getInt("SRVC_TRSFID"));
						
						transfert.setDemandeID(resultat.getInt("DEMANDEID"));
						transfert.setDemande(resultat.getString("NUMDEMANDE"));
						
						
						transfert.setDateDMD(resultat.getString("DATEDEMANDE"));*/
						listedemandetransfert.add(transfert);	
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}
				return listedemandetransfert;	
			}
			
			public void AjoutTransfert(Transfert transfert)
			{
				try
				{
					statement = conn.prepareStatement("INSERT INTO TRANSFERT(STATUTID,NUMTRANSFERT,USERID,DATETRANSFERT,SRVC_TRSFID,DEMANDEID) VALUES(?,?,?,?,?,?)");
					//statement.setInt(1, transfert.getTransfertID());
					statement.setInt(1, transfert.getStatutID());
					statement.setString(2, transfert.getNumTransfert());
					statement.setInt(3, transfert.getUserID());	
					statement.setString(4, transfert.getDateTransfert());
					statement.setInt(5, transfert.getServiceID());
					statement.setInt(6, transfert.getDemandeID());
					
					//System.out.println(transfert.getTransfertID());
					System.out.println(transfert.getStatutID());
					System.out.println(transfert.getNumTransfert());
					System.out.println(transfert.getUserID());
					System.out.println(transfert.getDateTransfert());
					System.out.println(transfert.getServiceID());
			
					statement.executeUpdate();
					System.out.println("inserer avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage()+"ajout");
				}
			}
			
			public Transfert rechercheTransfert(int idtransfert)
			{
				Transfert transfert = null;
				try
				{
					statement=conn.prepareStatement("SELECT * FROM TRANSFERT WHERE TRANSFERTID = ?");
					
					statement.setInt(1,idtransfert);
					
					resultat = statement.executeQuery();
					
					while(resultat.next())
					{
						transfert = new Transfert();
						transfert.setTransfertID(resultat.getInt("TRANSFERTID"));
						transfert.setStatutID(resultat.getInt("STATUTID"));
						transfert.setNumTransfert(resultat.getString("NUMTRANSFERT"));
						transfert.setUserID(resultat.getInt("USERID"));
						transfert.setDateTransfert(resultat.getString("DATETRANSFERT"));
						transfert.setServiceID(resultat.getInt("SRVC_TRSFID"));
						transfert.setDemandeID(resultat.getInt("DEMANDEID"));
					}
					statement.close();	
				}
				catch(SQLException ex)
				{
					
					System.out.println("recherche " +ex.getMessage());
				}
				return transfert;
			}
			
			public void updateTransfert(Transfert transfert)
			{
				try
				{
					statement = conn.prepareStatement("UPDATE TRANSFERT SET STATUTID = ?, NUMTRANSFERT = ?, USERID = ?, DATETRANSFERT = ?, SRVC_TRSFID = ?, DEMANDEID = ? WHERE TRANSFERTID = ?");
				
					statement.setInt(1, transfert.getStatutID());
					statement.setString(2, transfert.getNumTransfert());
					statement.setInt(3, transfert.getUserID());	
					statement.setString(4, transfert.getDateTransfert());
					statement.setInt(5, transfert.getServiceID());
					statement.setInt(6, transfert.getDemandeID());
					statement.setInt(7, transfert.getTransfertID());
					
					
					statement.executeUpdate();
					System.out.println("Update avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println(ex.getMessage());
				}	
			}
			
			public void deleteTransfert(int idtransfert)
			{
				try
				{
					
					statement = conn.prepareStatement("DELETE FROM TRANSFERT WHERE TRANSFERTID = ?");
					
					statement.setInt(1, idtransfert);
					
					statement.executeUpdate();
					
					System.out.println("suppression avec succes");
				}
				catch(SQLException ex)
				{
					System.out.println("suppression " +ex.getMessage()+" "+ idtransfert);
				}	
			}
	}



