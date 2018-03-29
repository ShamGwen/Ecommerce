package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="lignesCommandes")
public class LigneCommande implements Serializable{

	
	//Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_lc")
	private int idLC;
	private int quantite;
	private double prix;
	
	//Transformation de l'association UML en Java
	private Produit produit;
	@ManyToOne
	@JoinColumn(name="id_commande",referencedColumnName="id_com")
	private Commande commande;
	
	//Constructeurs0
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
