package modeleMateriel;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleMateriel {
	private java.sql.Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleMateriel(){
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
	
	public ArrayList<Materiel> listeMateriel(){
	ArrayList <Materiel> listemateriel = new ArrayList<Materiel>();
		try
		{
			statement = conn.prepareStatement("SELECT * FROM MATERIEL");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				Materiel materiel = new Materiel();
				materiel.setMaterielID(resultat.getInt("MATERIELID"));
				materiel.setCategorieID(resultat.getInt("CATEGORIEID"));
				materiel.setNomMateriel(resultat.getString("NOMMATERIEL"));
				materiel.setStock(resultat.getInt("STOCK"));
				materiel.setEtat(resultat.getString("ETAT"));
				materiel.setCodeMateriel(resultat.getString("CODEMATERIEL"));
	
				listemateriel.add(materiel);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listemateriel;	
	}
	
	public void AjoutMateriel(Materiel materiel)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO MATERIEL(MATERIELID,CATEGORIEID,NOMMATERIEL,STOCK,ETAT,CODEMATERIEL) VALUES(?,?,?,?,?,?)");
			statement.setInt(1, materiel.getMaterielID());
			statement.setInt(2, materiel.getCategorieID());
			statement.setString(3, materiel.getNomMateriel());
			statement.setInt(4, materiel.getStock());	
			statement.setString(5, materiel.getEtat());
			statement.setString(6, materiel.getCodeMateriel());
			
			System.out.println(materiel.getMaterielID());
			System.out.println(materiel.getCategorieID());
			System.out.println(materiel.getNomMateriel());
			System.out.println(materiel.getStock());
			System.out.println(materiel.getEtat());
			System.out.println(materiel.getCodeMateriel());
	
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage()+"ajout");
		}
	}
	
	public Materiel rechercheMateriel(int idmateriel)
	{
		Materiel materiel = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM MATERIEL WHERE MATERIELID = ?");
			
			statement.setInt(1,idmateriel);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				materiel = new Materiel();
				materiel.setMaterielID(resultat.getInt("MATERIELID"));
				materiel.setCategorieID(resultat.getInt("CATEGORIEID"));
				materiel.setNomMateriel(resultat.getString("NOMMATERIEL"));
				materiel.setStock(resultat.getInt("STOCK"));
				materiel.setEtat(resultat.getString("ETAT"));
				materiel.setCodeMateriel(resultat.getString("CODEMATERIEL"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return materiel;
	}
	
	public void updateMateriel(Materiel materiel)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE MATERIEL SET CATEGORIEID = ?, NOMMATERIEL = ?, STOCK = ?, ETAT = ?, CODEMATERIEL = ? WHERE MATERIELID = ?");
		
			statement.setInt(1, materiel.getCategorieID());
			statement.setString(2, materiel.getNomMateriel());
			statement.setInt(3, materiel.getStock());	
			statement.setString(4, materiel.getEtat());
			statement.setString(5, materiel.getCodeMateriel());
			statement.setInt(6, materiel.getMaterielID());
			
			
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteMateriel(int idmateriel)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM MATERIEL WHERE MATERIELID = ?");
			
			statement.setInt(1, idmateriel);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ idmateriel);
		}	
	}

}
