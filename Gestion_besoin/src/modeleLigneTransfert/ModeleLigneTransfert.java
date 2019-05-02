package modeleLigneTransfert;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleLigneTransfert {
	private java.sql.Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	public ModeleLigneTransfert() {
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
	
	
	
	public ArrayList<LigneTransfert> listeLigneTransfert(){
		ArrayList <LigneTransfert> listelignetransfert = new ArrayList<LigneTransfert>();
		try
		{
			statement = conn.prepareStatement("SELECT LIGNETRANSFERTID,LIGNE_TRANSFERT.TRANSFERTID,NUMTRANSFERT,NOMARTICLE,QTEARTICLETRANSFERT,COMMENTAIRETRANSFERT FROM LIGNE_TRANSFERT,TRANSFERT,ARTICLE WHERE LIGNE_TRANSFERT.TRANSFERTID = TRANSFERT.TRANSFERTID AND LIGNE_TRANSFERT.ARTICLEID = ARTICLE.ARTICLEID");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				LigneTransfert lignetransfert = new LigneTransfert();
				lignetransfert.setLigneTransfertID(resultat.getInt("LIGNETRANSFERTID"));
				lignetransfert.setTransfertID(resultat.getInt("TRANSFERTID"));
				lignetransfert.setTransfert(resultat.getString("NUMTRANSFERT"));
				lignetransfert.setArticle(resultat.getString("NOMARTICLE"));
				lignetransfert.setQteArticleTransfert(resultat.getInt("QTEARTICLETRANSFERT"));
				lignetransfert.setCommentaireTransfert(resultat.getString("COMMENTAIRETRANSFERT"));
				listelignetransfert.add(lignetransfert);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listelignetransfert;	
	}
	
	
	public ArrayList<LigneTransfert> listeDetailTrans(int transfertid){
		ArrayList <LigneTransfert> listelignedetail = new ArrayList<LigneTransfert>();
		try
		{
			statement = conn.prepareStatement("SELECT LIGNETRANSFERTID,LIGNE_TRANSFERT.TRANSFERTID,NUMTRANSFERT,NOMARTICLE,QTEARTICLETRANSFERT,COMMENTAIRETRANSFERT FROM LIGNE_TRANSFERT,TRANSFERT,ARTICLE WHERE LIGNE_TRANSFERT.TRANSFERTID = TRANSFERT.TRANSFERTID AND LIGNE_TRANSFERT.ARTICLEID = ARTICLE.ARTICLEID AND TRANSFERT.TRANSFERTID = ?");
			statement.setInt(1,transfertid);
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				LigneTransfert lignetransfert = new LigneTransfert();
				lignetransfert.setLigneTransfertID(resultat.getInt("LIGNETRANSFERTID"));
				lignetransfert.setTransfertID(resultat.getInt("TRANSFERTID"));
				lignetransfert.setTransfert(resultat.getString("NUMTRANSFERT"));
				lignetransfert.setArticle(resultat.getString("NOMARTICLE"));
				lignetransfert.setQteArticleTransfert(resultat.getInt("QTEARTICLETRANSFERT"));
				lignetransfert.setCommentaireTransfert(resultat.getString("COMMENTAIRETRANSFERT"));
				listelignedetail.add(lignetransfert);
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listelignedetail;	
	}
	
	public void AjoutLigneTransfert(LigneTransfert ligneTransfert)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO LIGNE_TRANSFERT(ARTICLEID,TRANSFERTID,QTEARTICLETRANSFERT,COMMENTAIRETRANSFERT) VALUES(?,?,?,?)");
			//statement.setInt(1, ligneTransfert.getLigneTransfertID());
			statement.setInt(1, ligneTransfert.getArticleID());
			statement.setInt(2, ligneTransfert.getTransfertID());
			statement.setInt(3, ligneTransfert.getQteArticleTransfert());	
			statement.setString(4, ligneTransfert.getCommentaireTransfert());
			
			//System.out.println(ligneTransfert.getLigneTransfertID());
			System.out.println(ligneTransfert.getArticleID());
			System.out.println(ligneTransfert.getTransfertID());
			System.out.println(ligneTransfert.getQteArticleTransfert());
			System.out.println(ligneTransfert.getCommentaireTransfert());
	
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage()+"ajout");
		}
	}
	
	public LigneTransfert rechercheLigneTransfert(int idlignetransfert)
	{
		LigneTransfert lignetransfert = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM LIGNE_TRANSFERT WHERE LIGNETRANSFERTID = ?");
			
			statement.setInt(1,idlignetransfert);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				lignetransfert = new LigneTransfert();
				lignetransfert.setLigneTransfertID(resultat.getInt("LIGNETRANSFERTID"));
				lignetransfert.setArticleID(resultat.getInt("ARTICLEID"));
				lignetransfert.setTransfertID(resultat.getInt("TRANSFERTID"));
				lignetransfert.setQteArticleTransfert(resultat.getInt("QTEARTICLETRANSFERT"));
				lignetransfert.setCommentaireTransfert(resultat.getString("COMMENTAIRETRANSFERT"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return lignetransfert;
	}
	
	public void updateLigneTransfert(LigneTransfert lignetransfert)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE LIGNE_TRANSFERT SET ARTICLEID = ?, TRANSFERTID = ?, QTEARTICLETRANSFERT = ?, COMMENTAIRETRANSFERT = ? WHERE LIGNETRANSFERTID = ?");
		
			statement.setInt(1, lignetransfert.getArticleID());
			statement.setInt(2, lignetransfert.getTransfertID());
			statement.setInt(3, lignetransfert.getQteArticleTransfert());	
			statement.setString(4, lignetransfert.getCommentaireTransfert());
			statement.setInt(5, lignetransfert.getLigneTransfertID());
			
			
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteLigneTransfert(int idlignetransfert)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM LIGNE_TRANSFERT WHERE LIGNETRANSFERTID = ?");
			
			statement.setInt(1, idlignetransfert);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ idlignetransfert);
		}	
	}

}
