package fr.adaming.model;

import java.io.Serializable;
import java.util.List;

public class Panier implements Serializable{

	//Attributs
	private int idPanier;

	//transformation de l'association UML en Java
	private List<LigneCommande> listeLC;
	
	//Constructeurs
	public Panier() {
		super();
	}

	public Panier(int idPanier) {
		super();
		this.idPanier = idPanier;
	}

	//Getters et setters
	public int getIdPanier() {
		return idPanier;
	}

	public void setIdPanier(int idPanier) {
		this.idPanier = idPanier;
	}

	public List<LigneCommande> getListeLC() {
		return listeLC;
	}

	public void setListeLC(List<LigneCommande> listeLC) {
		this.listeLC = listeLC;
	}

	//Methode toString
	@Override
	public String toString() {
		return "Panier [idPanier=" + idPanier + "]";
	}
	
	
}
