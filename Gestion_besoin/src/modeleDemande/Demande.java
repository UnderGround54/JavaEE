package modeleDemande;

/**
 * @author UnderGround
 *
 */
public class Demande {
	private int demandeID;
	private int statutID;
	private int userID;
	private int serviceID;
	private String dateDemande;
	private String numDemande;
	public int getDemandeID() {
		return demandeID;
	}
	public void setDemandeID(int demandeID) {
		this.demandeID = demandeID;
	}
	public int getStatutID() {
		return statutID;
	}
	public void setStatutID(int statutID) {
		this.statutID = statutID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	public String getDateDemande() {
		return dateDemande;
	}
	public void setDateDemande(String dateDemande) {
		this.dateDemande = dateDemande;
	}
	public String getNumDemande() {
		return numDemande;
	}
	public void setNumDemande(String numDemande) {
		this.numDemande = numDemande;
	}
	
	private String Statut;
	private String user;
	private String userPrenom;
	private String service;
	private String posteUser;
	private int serviceUserID;
	private int dateGraphe;
	
	public int getDateGraphe() {
		return dateGraphe;
	}
	public void setDateGraphe(int dateGraphe) {
		this.dateGraphe = dateGraphe;
	}
	
	public String getUserPrenom() {
		return userPrenom;
	}
	public void setUserPrenom(String userPrenom) {
		this.userPrenom = userPrenom;
	}
	public String getStatut() {
		return Statut;
	}
	public void setStatut(String statut) {
		Statut = statut;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getService() {
		return service;
	}
	public void setService(String service) {
		this.service = service;
	}
	public String getPosteUser() {
		return posteUser;
	}
	public void setPosteUser(String posteUser) {
		this.posteUser = posteUser;
	}
	public int getServiceUserID() {
		return serviceUserID;
	}
	public void setServiceUserID(int serviceUserID) {
		this.serviceUserID = serviceUserID;
	}
	
	
}
