package modeleMateriel;

public class Materiel {
	private int materielID;
	private int categorieID;
	private int Stock;
	private String nomMateriel;
	private String etat;
	private String codeMateriel;
	public int getMaterielID() {
		return materielID;
	}
	public void setMaterielID(int materielID) {
		this.materielID = materielID;
	}
	public int getCategorieID() {
		return categorieID;
	}
	public void setCategorieID(int categorieID) {
		this.categorieID = categorieID;
	}
	public int getStock() {
		return Stock;
	}
	public void setStock(int stock) {
		Stock = stock;
	}
	public String getNomMateriel() {
		return nomMateriel;
	}
	public void setNomMateriel(String nomMateriel) {
		this.nomMateriel = nomMateriel;
	}
	public String getEtat() {
		return etat;
	}
	public void setEtat(String etat) {
		this.etat = etat;
	}
	public String getCodeMateriel() {
		return codeMateriel;
	}
	public void setCodeMateriel(String codeMateriel) {
		this.codeMateriel = codeMateriel;
	}
	
}
