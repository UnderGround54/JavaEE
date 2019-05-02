package modeleDirection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleDirection {
	
	private java.sql.Connection conn =null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleDirection() {
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
	
	public ArrayList<Direction> listeDirection()
	{
		ArrayList <Direction> listedirection = new ArrayList<Direction>();
		try
		{
			statement = conn.prepareStatement("SELECT DIRECTIONID,LIBELLEDIRECTION,ABREVIATIONDIRECTION FROM DIRECTION");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				Direction direction = new Direction();
				direction.setDirectionID(resultat.getInt("DIRECTIONID"));
				direction.setLibelleDirection(resultat.getString("LIBELLEDIRECTION"));
				direction.setAbreviationDirection(resultat.getString("ABREVIATIONDIRECTION"));
	
				listedirection.add(direction);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listedirection;
	}
	
	public void AjoutDirection(Direction direction)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO DIRECTION(LIBELLEDIRECTION,ABREVIATIONDIRECTION) VALUES(?,?)");
			//statement.setInt(1, direction.getDirectionID());
			statement.setString(1, direction.getLibelleDirection());
			statement.setString(2, direction.getAbreviationDirection());
			
			/*System.out.println(direction.getDirectionID());
			System.out.println(direction.getdirectionID());*/
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public Direction rechercheDirection(int directionid)
	{
		Direction direction = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM DIRECTION WHERE DIRECTIONID = ?");
			
			statement.setInt(1,directionid);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				direction = new Direction();
				direction.setDirectionID(resultat.getInt("DIRECTIONID"));		
				direction.setLibelleDirection(resultat.getString("LIBELLEDIRECTION"));
				direction.setAbreviationDirection(resultat.getString("ABREVIATIONDIRECTION"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return direction;
	}

	public void updateDirection(Direction direction)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE DIRECTION SET LIBELLEDIRECTION = ?,ABREVIATIONDIRECTION = ? WHERE DIRECTIONID = ?");
		
			statement.setString(1, direction.getLibelleDirection());
			statement.setString(2, direction.getAbreviationDirection());
			statement.setInt(3, direction.getDirectionID());
			
			System.out.println(" ID "+direction.getDirectionID());
			System.out.println(" abreviation "+direction.getAbreviationDirection());
			System.out.println(" libelle "+direction.getLibelleDirection());
						
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteDirection(int directionid)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM DIRECTION WHERE DIRECTIONID = ?");
			
			statement.setInt(1, directionid);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ directionid);
		}	
	}

}
