package modeleLigneDemande;

public class LigneDemande{
	private int ligneDemandeID;
	private int categorieID;
	private int demandeID;
	private int qteArticleDemande;
	private String commentaireDemande;
	public int getLigneDemandeID() {
		return ligneDemandeID;
	}
	public void setLigneDemandeID(int ligneDemandeID) {
		this.ligneDemandeID = ligneDemandeID;
	}
	public int getCategorieID() {
		return categorieID;
	}
	public void setCategorieID(int categorieID) {
		this.categorieID = categorieID;
	}
	public int getDemandeID() {
		return demandeID;
	}
	public void setDemandeID(int demandeID) {
		this.demandeID = demandeID;
	}
	public int getQteArticleDemande() {
		return qteArticleDemande;
	}
	public void setQteArticleDemande(int qteArticleDemande) {
		this.qteArticleDemande = qteArticleDemande;
	}
	public String getCommentaireDemande() {
		return commentaireDemande;
	}
	public void setCommentaireDemande(String commentaireDemande) {
		this.commentaireDemande = commentaireDemande;
	}
	
	private String demande;
	private String categorie;

	public String getDemande() {
		return demande;
	}
	public void setDemande(String demande) {
		this.demande = demande;
	}
	public String getCategorie() {
		return categorie;
	}
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}
}
