package modeleLigneTransfert;

public class LigneTransfert {
	private int ligneTransfertID;
	private int transfertID;
	private int articleID;
	private int qteArticleTransfert;
	private String commentaireTransfert;
	public int getLigneTransfertID() {
		return ligneTransfertID;
	}
	public void setLigneTransfertID(int ligneTransfertID) {
		this.ligneTransfertID = ligneTransfertID;
	}
	public int getTransfertID() {
		return transfertID;
	}
	public void setTransfertID(int transfertID) {
		this.transfertID = transfertID;
	}
	public int getArticleID() {
		return articleID;
	}
	public void setArticleID(int articleID) {
		this.articleID = articleID;
	}
	public int getQteArticleTransfert() {
		return qteArticleTransfert;
	}
	public void setQteArticleTransfert(int qteArticleTransfert) {
		this.qteArticleTransfert = qteArticleTransfert;
	}
	public String getCommentaireTransfert() {
		return commentaireTransfert;
	}
	public void setCommentaireTransfert(String commentaireTransfert) {
		this.commentaireTransfert = commentaireTransfert;
	}
	
	private String transfert;
	private String article;
	public String getTransfert() {
		return transfert;
	}
	public void setTransfert(String transfert) {
		this.transfert = transfert;
	}
	public String getArticle() {
		return article;
	}
	public void setArticle(String article) {
		this.article = article;
	}
	
}
