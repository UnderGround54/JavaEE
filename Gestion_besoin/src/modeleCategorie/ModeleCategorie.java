package modeleCategorie;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleCategorie {
	private java.sql.Connection conn =null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleCategorie() {
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
	
	public ArrayList<Categorie> listeCategorie()
	{
		ArrayList <Categorie> listecategorie = new ArrayList<Categorie>();
		try
		{
			statement = conn.prepareStatement("SELECT * FROM CATEGORIE");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				Categorie categorie = new Categorie();
				categorie.setCategorieID(resultat.getInt("CATEGORIEID"));
				categorie.setLibelleCategorie(resultat.getString("LIBELLECATEGORIE"));			
	
				listecategorie.add(categorie);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listecategorie;
	}
	
	public void AjoutCategorie(Categorie categorie)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO CATEGORIE(LIBELLECATEGORIE) VALUES(?)");
			//statement.setInt(1, categorie.getCategorieID());
			statement.setString(1, categorie.getLibelleCategorie());
			
			/*System.out.println(categorie.getCategorieID());
			System.out.println(categorie.getcategorieID());*/
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public Categorie rechercheCategorie(int categorieid)
	{
		Categorie categorie = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM CATEGORIE WHERE CATEGORIEID = ?");
			
			statement.setInt(1,categorieid);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				categorie = new Categorie();
				categorie.setCategorieID(resultat.getInt("CATEGORIEID"));		
				categorie.setLibelleCategorie(resultat.getString("LIBELLECATEGORIE"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return categorie;
	}

	public void updateCategorie(Categorie categorie)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE CATEGORIE SET LIBELLECATEGORIE = ? WHERE CATEGORIEID = ?");
		
			statement.setString(1, categorie.getLibelleCategorie());
			statement.setInt(2, categorie.getCategorieID());
						
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteCategorie(int categorieid)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM CATEGORIE WHERE CATEGORIEID = ?");
			
			statement.setInt(1, categorieid);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ categorieid);
		}	
	}

}
