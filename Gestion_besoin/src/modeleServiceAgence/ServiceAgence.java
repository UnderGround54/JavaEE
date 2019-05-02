package modeleServiceAgence;

public class ServiceAgence {
	private int serviceID;
	private int directionID;
	private String direction;
	private String nomService;
	private String typeService;
	private String lieuService;
	private String codeService;
	private String abreviationService;
	public int getServiceID() {
		return serviceID;
	}
	public void setServiceID(int serviceID) {
		this.serviceID = serviceID;
	}
	
	public int getDirectionID() {
		return directionID;
	}
	public void setDirectionID(int directionID) {
		this.directionID = directionID;
	}
	
	public String getDirection() {
		return direction;
	}
	public void setDirection(String direction) {
		this.direction = direction;
	}
	public String getNomService() {
		return nomService;
	}
	public void setNomService(String nomService) {
		this.nomService = nomService;
	}
	public String getTypeService() {
		return typeService;
	}
	public void setTypeService(String typeService) {
		this.typeService = typeService;
	}
	public String getLieuService() {
		return lieuService;
	}
	public void setLieuService(String lieuService) {
		this.lieuService = lieuService;
	}
	
	public String getAbreviationService() {
		return abreviationService;
	}
	public void setAbreviationService(String abreviationService) {
		this.abreviationService = abreviationService;
	}
	public String getCodeService() {
		return codeService;
	}
	public void setCodeService(String codeService) {
		this.codeService = codeService;
	}
	
}
