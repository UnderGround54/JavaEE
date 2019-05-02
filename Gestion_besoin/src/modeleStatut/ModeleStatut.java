package modeleStatut;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modeleStatut.Statut;

public class ModeleStatut {
	private java.sql.Connection conn =null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleStatut() {
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
	
	public ArrayList<Statut> listeStatut()
	{
		ArrayList<Statut> listestatut = new ArrayList<Statut>();
		try
		{
			statement = conn.prepareStatement("SELECT * FROM STATUT");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				Statut statut = new Statut();
				statut.setStatutID(resultat.getInt("STATUTID"));
				statut.setLibelleStatut(resultat.getString("LIBELLESTATUT"));			
	
				listestatut.add(statut);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listestatut;
	}
	
	public void AjoutStatut(Statut statut)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO STATUT(LIBELLESTATUT) VALUES(?)");
			//statement.setInt(1, statut.getStatutID());
			statement.setString(1, statut.getLibelleStatut());
			
			/*System.out.println(statut.getStatutID());
			System.out.println(statut.getstatutID());*/
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public Statut rechercheStatut(int statutid)
	{
		Statut statut = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM STATUT WHERE STATUTID = ?");
			
			statement.setInt(1,statutid);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				statut = new Statut();
				statut.setStatutID(resultat.getInt("STATUTID"));		
				statut.setLibelleStatut(resultat.getString("LIBELLESTATUT"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return statut;
	}

	public void updateStatut(Statut statut)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE STATUT SET LIBELLESTATUT = ? WHERE STATUTID = ?");
		
			statement.setString(1, statut.getLibelleStatut());
			statement.setInt(2, statut.getStatutID());
						
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteStatut(int statutid)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM STATUT WHERE STATUTID = ?");
			
			statement.setInt(1, statutid);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ statutid);
		}	
	}

}
