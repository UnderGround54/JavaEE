package modeleTransfert;

public class Transfert {
	private int transfertID;
	private int demandeID;
	private int serviceID;
	private int	statutID;
	private int userID;
	private String numTransfert;
	private String dateTransfert;
	public int getTransfertID() {
		return transfertID;
	}
	public void setTransfertID(int transfertID) {
		this.transfertID = transfertID;
	}
	public int getDemandeID() {
		return demandeID;
	}
	public void setDemandeID(int demandeID) {
		this.demandeID = demandeID;
	}
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
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
	public String getNumTransfert() {
		return numTransfert;
	}
	public void setNumTransfert(String numTransfert) {
		this.numTransfert = numTransfert;
	}
	public String getDateTransfert() {
		return dateTransfert;
	}
	public void setDateTransfert(String dateTransfert) {
		this.dateTransfert = dateTransfert;
	}
	
	private String statut;
	private String user;
	private String service;
	private String demande;
	private String DateDMD;
	private int articleID;
	private int serviceUserID;
	
	public int getServiceUserID() {
		return serviceUserID;
	}
	public void setServiceUserID(int serviceUserID) {
		this.serviceUserID = serviceUserID;
	}
	
	public int getArticleID() {
		return articleID;
	}
	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}
	public String getStatut() {
		return statut;
	}
	public void setStatut(String statut) {
		this.statut = statut;
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
	public String getDemande() {
		return demande;
	}
	public void setDemande(String demande) {
		this.demande = demande;
	}
	public String getDateDMD() {
		return DateDMD;
	}
	public void setDateDMD(String dateDMD) {
		DateDMD = dateDMD;
	}
	

}
