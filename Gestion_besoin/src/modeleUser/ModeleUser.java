package modeleUser;
//gestion_de_besoin
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ModeleUser {
	
	private Connection conn =null;
	private PreparedStatement statement = null;
	private ResultSet resultat = null;
	
	public ModeleUser() {
    	try
		{
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@//localhost:1521/XE","system","prosper 54");
		}
		catch(ClassNotFoundException ex)
		{
			System.out.println("Probleme de pilote base de donneées");
			System.out.println(ex.getMessage());
		}
		catch(SQLException ex)
		{
			System.out.println("Probleme de connexion ou de requetes");
			System.out.println(ex.getMessage());
		}
    }
	
	public ArrayList<Utilisateur> listeUser()
	{
		ArrayList <Utilisateur> liste = new ArrayList<Utilisateur>();
		try
		{
			statement = conn.prepareStatement("SELECT USERID,NOMSRVC_AGC,NOMUSER,PRENOMUSER,LOGINUSER,ADRESSEUSER,MOTDEPASS,POSTEUSER,DROITUSER FROM UTILISATEUR,SERVICE_AGENCE WHERE UTILISATEUR.SRVC_AGCID = SERVICE_AGENCE.SRVC_AGCID ORDER BY NOMUSER ASC");
			resultat = statement.executeQuery();
			while(resultat.next())
			{
				Utilisateur user = new Utilisateur();
				user.setUserID(resultat.getInt("USERID"));
				user.setService(resultat.getString("NOMSRVC_AGC"));
				user.setNomUser(resultat.getString("NOMUSER"));
				user.setPrenomUser(resultat.getString("PRENOMUSER"));
				user.setLoginUser(resultat.getString("LOGINUSER"));
				user.setAdresseUser(resultat.getString("ADRESSEUSER"));
				user.setMotDePass(resultat.getString("MOTDEPASS"));
				user.setPosteUser(resultat.getString("POSTEUSER"));
				user.setDroitUser(resultat.getInt("DROITUSER"));
				
				//user.setServiceID(resultat.getInt("SRVC_AGCID"));
	
				liste.add(user);	
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
		return liste;
	}
	
	public void AjoutUser(Utilisateur user)
	{
		try
		{
			statement = conn.prepareStatement("INSERT INTO UTILISATEUR(DROITUSER,SRVC_AGCID,NOMUSER,PRENOMUSER,ADRESSEUSER,LOGINUSER,MOTDEPASS,POSTEUSER) VALUES(?,?,?,?,?,?,?,?)");
			
			//statement.setInt(1, user.getUserID());
			statement.setInt(1, user.getDroitUser());
			statement.setInt(2, user.getServiceID());
			statement.setString(3, user.getNomUser());
			statement.setString(4, user.getPrenomUser());	
			statement.setString(5, user.getAdresseUser());
			statement.setString(6, user.getLoginUser());
			statement.setString(7, user.getMotDePass());
			statement.setString(8, user.getPosteUser());
			/*System.out.println(user.getPrenomUser());
			System.out.println(user.getNomUser());
			System.out.println( user.getAdresseUser());
			System.out.println(user.getLoginUser());
			System.out.println(user.getPosteUser());
			System.out.println(user.getMotDePass());
			System.out.println( user.getDroitUser());
			System.out.println(user.getServiceID());
			System.out.println(user.getUserID());*/
			statement.executeUpdate();
			System.out.println("inserer avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}
	}
	
	public Utilisateur rechercheUser(int id)
	{
		Utilisateur user = null;
		try
		{
			statement=conn.prepareStatement("SELECT * FROM UTILISATEUR WHERE USERID = ?");
			
			statement.setInt(1,id);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				user = new Utilisateur();
				user.setUserID(resultat.getInt("USERID"));
				user.setNomUser(resultat.getString("NOMUSER"));
				user.setPrenomUser(resultat.getString("PRENOMUSER"));
				user.setLoginUser(resultat.getString("LOGINUSER"));
				user.setAdresseUser(resultat.getString("ADRESSEUSER"));
				user.setMotDePass(resultat.getString("MOTDEPASS"));
				user.setPosteUser(resultat.getString("POSTEUSER"));
				user.setDroitUser(resultat.getInt("DROITUSER"));
				user.setServiceID(resultat.getInt("SRVC_AGCID"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recherche " + ex.getMessage()+"USERID "+ id);
		}
		return user;
	}

	public void updateUser(Utilisateur user)
	{
		try
		{
			statement = conn.prepareStatement("UPDATE UTILISATEUR SET PRENOMUSER=?, NOMUSER=?, ADRESSEUSER=?, LOGINUSER=?, POSTEUSER=?, MOTDEPASS=?, DROITUSER=?, SRVC_AGCID=? WHERE USERID=?");
		
			statement.setString(1, user.getPrenomUser());
			statement.setString(2, user.getNomUser() );
			statement.setString(3, user.getAdresseUser());
			statement.setString(4, user.getLoginUser());
			statement.setString(5, user.getPosteUser());
			statement.setString(6, user.getMotDePass());
			statement.setInt(7, user.getDroitUser());
			statement.setInt(8, user.getServiceID());
			statement.setInt(9,  user.getUserID());
			
			statement.executeUpdate();
			System.out.println("Update avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println(ex.getMessage());
		}	
	}
	
	
	public ArrayList<Utilisateur> validerLogin(String login, String pass)
	{
		ArrayList<Utilisateur> log= new ArrayList<Utilisateur>();
		try
		{
			statement= conn.prepareStatement("SELECT * FROM UTILISATEUR,SERVICE_AGENCE WHERE UTILISATEUR.SRVC_AGCID = SERVICE_AGENCE.SRVC_AGCID AND LOGINUSER=? AND MOTDEPASS=?");
			
			statement.setString(1,login);
			statement.setString(2,pass);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				Utilisateur user = new Utilisateur();
				
				user.setUserID(resultat.getInt("USERID"));
				user.setNomUser(resultat.getString("NOMUSER"));
				user.setPrenomUser(resultat.getString("PRENOMUSER"));
				user.setLoginUser(resultat.getString("LOGINUSER"));
				user.setAdresseUser(resultat.getString("ADRESSEUSER"));
				user.setMotDePass(resultat.getString("MOTDEPASS"));
				user.setPosteUser(resultat.getString("POSTEUSER"));
				user.setDroitUser(resultat.getInt("DROITUSER"));
				user.setServiceID(resultat.getInt("SRVC_AGCID"));
				user.setTypeService(resultat.getString("TYPESRVC_AGC"));
				log.add(user);
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("Error");
		}
		return log;
	}
	
	public void deleteUser(int id)
	{
		try
		{
			System.out.println("suppression avant");
			statement = conn.prepareStatement("DELETE FROM UTILISATEUR WHERE USERID = ?");
			
			statement.setInt(1, id);
			
			statement.executeUpdate();
			
			System.out.println("suppression avec succes");
		}
		catch(SQLException ex)
		{
			System.out.println("suppression " +ex.getMessage()+" "+ id);
		}	
	}

	/*public Utilisateur receiveDemande(int idService)
	{
		Utilisateur user=null;
		try {
			statement= conn.prepareStatement("SELECT * FROM UTILISATEUR WHERE SRVC_AGCID=?");
			
			statement.setInt(1,idService);
			
			resultat = statement.executeQuery();
			
			while(resultat.next())
			{
				user = new Utilisateur();
				user.setUserID(resultat.getInt("USERID"));
				user.setNomUser(resultat.getString("NOMUSER"));
				user.setPrenomUser(resultat.getString("PRENOMUSER"));
				user.setLoginUser(resultat.getString("LOGINUSER"));
				user.setAdresseUser(resultat.getString("ADRESSEUSER"));
				user.setMotDePass(resultat.getString("MOTDEPASS"));
				user.setPosteUser(resultat.getString("POSTEUSER"));
				user.setDroitUser(resultat.getInt("DROITUSER"));
				user.setServiceID(resultat.getInt("SRVC_AGCID"));
			}
			statement.close();	
		}
		catch(SQLException ex)
		{
			
			System.out.println("recevoir " + ex.getMessage()+"USERID ");
		}
		return user;

	}*/
}
