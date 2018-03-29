package fr.adaming.model;

public class LigneCommande {

	
	//Attributs
	private int idLC;
	private int quantite;
	private double prix;
	
	//Constructeurs
	public LigneCommande() {
		super();
	}
	
	public LigneCommande(int quantite, double prix) {
		super();
		this.quantite = quantite;
		this.prix = prix;
	}
	
	public LigneCommande(int idLC, int quantite, double prix) {
		super();
		this.idLC = idLC;
		this.quantite = quantite;
		this.prix = prix;
	}
	
	//Getters et setters
	public int getIdLC() {
		return idLC;
	}
	
	public void setIdLC(int idLC) {
		this.idLC = idLC;
	}
	
	public int getQuantite() {
		return quantite;
	}
	
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	
	public double getPrix() {
		return prix;
	}
	
	public void setPrix(double prix) {
		this.prix = prix;
	}
	
	//Redefinition de la methode toString
	@Override
	public String toString() {
		return "LigneCommande [idLC=" + idLC + ", quantite=" + quantite + ", prix=" + prix + "]";
	}
	
	
}
