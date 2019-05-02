package modeleLigneDemande;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleLigneDemande {
	private java.sql.Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleLigneDemande() {
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
	
	public ArrayList<LigneDemande> listeLigneDemande(){
		ArrayList <LigneDemande> listelignedemande = new ArrayList<LigneDemande>();
		try
		{
			statement = conn.prepareStatement("SELECT DEMANDE.DEMANDEID,LIGNE_DEMANDE.CATEGORIEID,LIGNEDEMANDEID,NUMDEMANDE ,LIBELLECATEGORIE,QTEARTICLEDEMANDE,COMMENTAIREDEMANDE FROM LIGNE_DEMANDE,DEMANDE,CATEGORIE WHERE LIGNE_DEMANDE.DEMANDEID = DEMANDE.DEMANDEID  AND LIGNE_DEMANDE.CATEGORIEID = CATEGORIE.CATEGORIEID");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				LigneDemande lignedemande = new LigneDemande();
				lignedemande.setLigneDemandeID(resultat.getInt("LIGNEDEMANDEID"));
				lignedemande.setDemandeID(resultat.getInt("DEMANDEID"));
				lignedemande.setDemande(resultat.getString("NUMDEMANDE"));
				lignedemande.setCategorieID(resultat.getInt("CATEGORIEID"));
				lignedemande.setCategorie(resultat.getString("LIBELLECATEGORIE"));
				lignedemande.setQteArticleDemande(resultat.getInt("QTEARTICLEDEMANDE"));
				lignedemande.setCommentaireDemande(resultat.getString("COMMENTAIREDEMANDE"));
				listelignedemande.add(lignedemande);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listelignedemande;	
	}
	
	
	public ArrayList<LigneDemande> listeDetail(int demandeid){
		ArrayList <LigneDemande> listelignedetail = new ArrayList<LigneDemande>();
		try
		{
			statement = conn.prepareStatement("SELECT LIGNEDEMANDEID,LIGNE_DEMANDE.DEMANDEID,NUMDEMANDE,LIBELLECATEGORIE,QTEARTICLEDEMANDE,COMMENTAIREDEMANDE FROM LIGNE_DEMANDE,DEMANDE,CATEGORIE WHERE LIGNE_DEMANDE.DEMANDEID = DEMANDE.DEMANDEID  AND LIGNE_DEMANDE.CATEGORIEID = CATEGORIE.CATEGORIEID AND DEMANDE.DEMANDEID = ?");
			statement.setInt(1,demandeid);
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				LigneDemande lignedemande = new LigneDemande();
				lignedemande.setLigneDemandeID(resultat.getInt("LIGNEDEMANDEID"));
				lignedemande.setDemandeID(resultat.getInt("DEMANDEID"));
				lignedemande.setDemande(resultat.getString("NUMDEMANDE"));
				lignedemande.setCategorie(resultat.getString("LIBELLECATEGORIE"));
				lignedemande.setQteArticleDemande(resultat.getInt("QTEARTICLEDEMANDE"));
				lignedemande.setCommentaireDemande(resultat.getString("COMMENTAIREDEMANDE"));
				listelignedetail.add(lignedemande);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		
		return listelignedetail;	
	}
	
	public void AjoutLigneDemande(LigneDemande ligneDemande)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO LIGNE_DEMANDE(CATEGORIEID,DEMANDEID,QTEARTICLEDEMANDE,COMMENTAIREDEMANDE) VALUES(?,?,?,?)");
			//statement.setInt(1, ligneDemande.getLigneDemandeID());
			statement.setInt(1, ligneDemande.getCategorieID());
			statement.setInt(2, ligneDemande.getDemandeID());
			statement.setInt(3, ligneDemande.getQteArticleDemande());	
			statement.setString(4, ligneDemande.getCommentaireDemande());
			
			//System.out.println(ligneDemande.getLigneDemandeID());
			System.out.println(ligneDemande.getCategorieID());
			System.out.println(ligneDemande.getDemandeID());
			System.out.println(ligneDemande.getQteArticleDemande());
			System.out.println(ligneDemande.getCommentaireDemande());
	
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage()+"ajout");
		}
	}
	
	public LigneDemande rechercheLigneDemande(int idlignedemande)
	{
		LigneDemande lignedemande = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM LIGNE_DEMANDE WHERE LIGNEDEMANDEID = ?");
			
			statement.setInt(1,idlignedemande);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				lignedemande = new LigneDemande();
				lignedemande.setLigneDemandeID(resultat.getInt("LIGNEDEMANDEID"));
				lignedemande.setCategorieID(resultat.getInt("CATEGORIEID"));
				lignedemande.setDemandeID(resultat.getInt("DEMANDEID"));
				lignedemande.setQteArticleDemande(resultat.getInt("QTEARTICLEDEMANDE"));
				lignedemande.setCommentaireDemande(resultat.getString("COMMENTAIREDEMANDE"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return lignedemande;
	}
	
	public void updateLigneDemande(LigneDemande lignedemande)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE LIGNE_DEMANDE SET CATEGORIEID = ?, DEMANDEID = ?, QTEARTICLEDEMANDE = ?, COMMENTAIREDEMANDE = ? WHERE LIGNEDEMANDEID = ?");
		
			statement.setInt(1, lignedemande.getCategorieID());
			statement.setInt(2, lignedemande.getDemandeID());
			statement.setInt(3, lignedemande.getQteArticleDemande());	
			statement.setString(4, lignedemande.getCommentaireDemande());
			statement.setInt(5, lignedemande.getLigneDemandeID());
			
			
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteLigneDemande(int idlignedemande)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM LIGNE_DEMANDE WHERE LIGNEDEMANDEID = ?");
			
			statement.setInt(1, idlignedemande);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ idlignedemande);
		}	
	}

}
