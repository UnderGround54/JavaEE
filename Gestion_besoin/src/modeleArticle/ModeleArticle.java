package modeleArticle;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleArticle {
	private java.sql.Connection conn = null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleArticle(){
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
	
		public ArrayList<Article> listeArticle(){
		ArrayList <Article> listearticle = new ArrayList<Article>();
		try
		{
			statement = conn.prepareStatement("SELECT CATEGORIE.CATEGORIEID,ARTICLEID,LIBELLECATEGORIE,NOMARTICLE,STOCK,ETAT,CODEARTICLE FROM ARTICLE,CATEGORIE WHERE ARTICLE.CATEGORIEID = CATEGORIE.CATEGORIEID");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				Article article = new Article();
				article.setArticleID(resultat.getInt("ARTICLEID"));
				article.setCategorieID(resultat.getInt("CATEGORIEID"));
				article.setCategorie(resultat.getString("LIBELLECATEGORIE"));
				article.setNomArticle(resultat.getString("NOMARTICLE"));
				article.setStock(resultat.getInt("STOCK"));
				article.setEtat(resultat.getString("ETAT"));
				article.setCodeArticle(resultat.getString("CODEARTICLE"));
	
				listearticle.add(article);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return listearticle;	
	 }
		
		public ArrayList<Article> listeArticleLigne(int id){
			ArrayList <Article> listearticleligne = new ArrayList<Article>();
			try
			{
				statement = conn.prepareStatement("SELECT CATEGORIE.CATEGORIEID,ARTICLEID,LIBELLECATEGORIE,NOMARTICLE,STOCK,ETAT,CODEARTICLE FROM ARTICLE,CATEGORIE WHERE ARTICLE.CATEGORIEID = CATEGORIE.CATEGORIEID AND ARTICLE.ARTICLEID = ?");
				statement.setInt(1,id);
				resultat = statement.executeQuery();
				while(resultat.next())
				{
					Article article = new Article();
					article.setArticleID(resultat.getInt("ARTICLEID"));
					article.setCategorieID(resultat.getInt("CATEGORIEID"));
					article.setCategorie(resultat.getString("LIBELLECATEGORIE"));
					article.setNomArticle(resultat.getString("NOMARTICLE"));
					article.setStock(resultat.getInt("STOCK"));
					article.setEtat(resultat.getString("ETAT"));
					article.setCodeArticle(resultat.getString("CODEARTICLE"));
		
					listearticleligne.add(article);	
				}
				statement.close();	
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
			return listearticleligne;	
		 }
		
		public ArrayList<Article> listeArticleComande(){
			ArrayList <Article> listearticlecmd = new ArrayList<Article>();
			try
			{
				statement = conn.prepareStatement("SELECT ARTICLEID,LIBELLECATEGORIE,NOMARTICLE,STOCK,ETAT,CODEARTICLE FROM ARTICLE,CATEGORIE WHERE ARTICLE.CATEGORIEID = CATEGORIE.CATEGORIEID");
				resultat = statement.executeQuery();
				while(resultat.next())
				{
					Article article = new Article();
					article.setArticleID(resultat.getInt("ARTICLEID"));
					//article.setCategorieID(resultat.getInt("CATEGORIEID"));
					article.setCategorie(resultat.getString("LIBELLECATEGORIE"));
					article.setNomArticle(resultat.getString("NOMARTICLE"));
					article.setStock(resultat.getInt("STOCK"));
					article.setEtat(resultat.getString("ETAT"));
					article.setCodeArticle(resultat.getString("CODEARTICLE"));
		
					listearticlecmd.add(article);	
				}
				statement.close();	
			}
			catch(SQLException ex)
			{
				System.out.println(ex.getMessage());
			}
			return listearticlecmd;	
		}
	
	public void AjoutArticle(Article article)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO ARTICLE(CATEGORIEID,NOMARTICLE,STOCK,ETAT,CODEARTICLE) VALUES(?,?,?,?,?)");
			
			statement.setInt(1, article.getCategorieID());
			statement.setString(2, article.getNomArticle());
			statement.setInt(3, article.getStock());	
			statement.setString(4, article.getEtat());
			statement.setString(5, article.getCodeArticle());
			
			System.out.println(article.getCategorieID());
			System.out.println(article.getNomArticle());
			System.out.println(article.getStock());
			System.out.println(article.getEtat());
			System.out.println(article.getCodeArticle());
	
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage()+"ajout");
		}
	}
	
	public Article rechercheArticle(int idarticle)
	{
		Article article = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM ARTICLE WHERE ARTICLEID = ?");
			
			statement.setInt(1,idarticle);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				article = new Article();
				article.setArticleID(resultat.getInt("ARTICLEID"));
				article.setCategorieID(resultat.getInt("CATEGORIEID"));
				article.setNomArticle(resultat.getString("NOMARTICLE"));
				article.setStock(resultat.getInt("STOCK"));
				article.setEtat(resultat.getString("ETAT"));
				article.setCodeArticle(resultat.getString("CODEARTICLE"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " +ex.getMessage());
		}
		return article;
	}
	
	public void updateArticle(Article article)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE ARTICLE SET CATEGORIEID = ?, NOMARTICLE = ?, STOCK = ?, ETAT = ?, CODEARTICLE = ? WHERE ARTICLEID = ?");
		
			statement.setInt(1, article.getCategorieID());
			statement.setString(2, article.getNomArticle());
			statement.setInt(3, article.getStock());	
			statement.setString(4, article.getEtat());
			statement.setString(5, article.getCodeArticle());
			statement.setInt(6, article.getArticleID());
			
			
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	public void deleteArticle(int idarticle)
	{
		try
		{
			
			statement = conn.prepareStatement("DELETE FROM ARTICLE WHERE ARTICLEID = ?");
			
			statement.setInt(1, idarticle);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ idarticle);
		}	
	}

}
