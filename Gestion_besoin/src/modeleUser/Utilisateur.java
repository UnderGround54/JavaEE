package modeleUser;

public class Utilisateur {
	private int userID;
	private int droitUser;
	private int serviceID;
	private String service;
	private String nomUser;
	private String prenomUser;
	private String loginUser;
	private String adresseUser;
	private String MotDePass;
	private String posteUser;
	private String typeService;
	
	public String getTypeService() {
		return typeService;
	}
	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getDroitUser() {
		return droitUser;
	}
	public void setDroitUser(int droitUser) {
		this.droitUser = droitUser;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getNomUser() {
		return nomUser;
	}
	public void setNomUser(String nomUser) {
		this.nomUser = nomUser;
	}
	public String getPrenomUser() {
		return prenomUser;
	}
	public void setPrenomUser(String prenomUser) {
		this.prenomUser = prenomUser;
	}
	public String getLoginUser() {
		return loginUser;
	}
	public void setLoginUser(String loginUser) {
		this.loginUser = loginUser;
	}
	public String getAdresseUser() {
		return adresseUser;
	}
	public void setAdresseUser(String adresseUser) {
		this.adresseUser = adresseUser;
	}
	public String getMotDePass() {
		return MotDePass;
	}
	public void setMotDePass(String motDePass) {
		MotDePass = motDePass;
	}
	public String getPosteUser() {
		return posteUser;
	}
	public void setPosteUser(String posteUser) {
		this.posteUser = posteUser;
	}

}
