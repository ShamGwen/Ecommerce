package fr.adaming.model;

import java.util.Date;

public class Commande {

	//Attributs
	private long idCommande;
	private Date dateCommande;
	
	//Constructeurs
	public Commande() {
		super();
	}
	
	public Commande(Date dateCommande) {
		super();
		this.dateCommande = dateCommande;
	}
	
	public Commande(long idCommande, Date dateCommande) {
		super();
		this.idCommande = idCommande;
		this.dateCommande = dateCommande;
	}
	
	//Getters et setters
	public long getIdCommande() {
		return idCommande;
	}
	
	public void setIdCommande(long idCommande) {
		this.idCommande = idCommande;
	}
	
	public Date getDateCommande() {
		return dateCommande;
	}
	
	public void setDateCommande(Date dateCommande) {
		this.dateCommande = dateCommande;
	}
	
	//Redefinition de la methode toString
	@Override
	public String toString() {
		return "Commande [idCommande=" + idCommande + ", dateCommande=" + dateCommande + "]";
	}
	
	
}
