package modeleServiceAgence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleServiceAgence {

	private Connection conn =null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleServiceAgence() {
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
	
	public ArrayList<ServiceAgence> listeServiceAgence()
	{
		ArrayList <ServiceAgence> listeservice = new ArrayList<ServiceAgence>();
		try
		{
			statement = conn.prepareStatement("SELECT SRVC_AGCID,LIBELLEDIRECTION,NOMSRVC_AGC,TYPESRVC_AGC,LIEUSRVC_AGC,ABREVIATIONSRVC_AGC,CODESRVC_AGC FROM SERVICE_AGENCE,DIRECTION WHERE DIRECTION.DIRECTIONID = SERVICE_AGENCE.DIRECTIONID");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				ServiceAgence service = new ServiceAgence();
				service.setServiceID(resultat.getInt("SRVC_AGCID"));
				//service.setDirectionID(resultat.getInt("DIRECTIONID"));
				service.setDirection(resultat.getString("LIBELLEDIRECTION"));
				service.setNomService(resultat.getString("NOMSRVC_AGC"));
				service.setTypeService(resultat.getString("TYPESRVC_AGC"));
				service.setLieuService(resultat.getString("LIEUSRVC_AGC"));
				service.setAbreviationService(resultat.getString("ABREVIATIONSRVC_AGC"));
				service.setCodeService(resultat.getString("CODESRVC_AGC"));			
	
				listeservice.add(service);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listeservice;
	}
	
	public void AjoutService(ServiceAgence service)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO SERVICE_AGENCE(DIRECTIONID,NOMSRVC_AGC,TYPESRVC_AGC,ABREVIATIONSRVC_AGC,LIEUSRVC_AGC,CODESRVC_AGC) VALUES(?,?,?,?,?,?)");
			//statement.setInt(1, service.getServiceID());
			statement.setInt(1, service.getDirectionID());
			statement.setString(2, service.getNomService());
			statement.setString(3, service.getTypeService());
			statement.setString(4, service.getAbreviationService());
			statement.setString(5, service.getLieuService());
			statement.setString(6, service.getCodeService());
			
			
			/*System.out.println(service.getDirectionID());
			System.out.println(service.getNomService());
			System.out.println(service.getTypeService());
			System.out.println(service.getLieuService());
			System.out.println(service.getCodeService());
			System.out.println(service.getServiceID());*/
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public ServiceAgence rechercheService(int idservice)
	{
		ServiceAgence service = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM SERVICE_AGENCE WHERE SRVC_AGCID = ?");
			
			statement.setInt(1,idservice);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				service = new ServiceAgence();
				service.setServiceID(resultat.getInt("SRVC_AGCID"));
				service.setDirectionID(resultat.getInt("DIRECTIONID"));
				service.setNomService(resultat.getString("NOMSRVC_AGC"));
				service.setAbreviationService(resultat.getString("ABREVIATIONSRVC_AGC"));
				service.setTypeService(resultat.getString("TYPESRVC_AGC"));
				service.setLieuService(resultat.getString("LIEUSRVC_AGC"));
				service.setCodeService(resultat.getString("CODESRVC_AGC"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return service;
	}

	public void updateService(ServiceAgence service)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE SERVICE_AGENCE SET DIRECTIONID = ?, NOMSRVC_AGC = ?, TYPESRVC_AGC = ?, LIEUSRVC_AGC = ?, ABREVIATIONSRVC_AGC = ?, CODESRVC_AGC = ? WHERE SRVC_AGCID = ?");
		
				
			statement.setInt(1, service.getDirectionID());
			statement.setString(2, service.getNomService());
			statement.setString(3, service.getTypeService());	
			statement.setString(4, service.getLieuService());
			statement.setString(5, service.getAbreviationService());
			statement.setString(6, service.getCodeService());
			statement.setInt(7, service.getServiceID());
			
			/*System.out.println(service.getDirectionID());
			System.out.println(service.getNomService());
			System.out.println(service.getTypeService());
			System.out.println(service.getLieuService());
			System.out.println(service.getCodeService());
			System.out.println(service.getServiceID());*/
			
			
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteService(int idservice)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM SERVICE_AGENCE WHERE SRVC_AGCID = ?");
			
			statement.setInt(1, idservice);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ idservice);
		}	
	}
}
