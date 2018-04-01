package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

@ManagedBean(name="panierMB")
@SessionScoped
public class PanierMB implements Serializable{
	
	//attributs
	private Panier panier;
	private HttpSession maSession;
	private LigneCommande lcom;
	private double montantTotal;
	private Produit produit;
	
	//transformation de l'association uml en java
	
	private List<LigneCommande> listeLC;

	//constructeur vide
	public PanierMB() {
		this.panier = new Panier();
		this.lcom = new LigneCommande();
		
	}
	
	//methode init
	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	//Getters et setters
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public List<LigneCommande> getListeLC() {
		return listeLC;
	}

	public void setListeLC(List<LigneCommande> listeLC) {
		this.listeLC = listeLC;
	}
	
	public LigneCommande getLcom() {
		return lcom;
	}

	public void setLcom(LigneCommande lcom) {
		this.lcom = lcom;
	}

	
	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	//Methodes metiers
	public String ajouterLCdansPanier(){
		//calculer le prix de la ligne de commande
		int qtDemande = this.lcom.getQuantite();
		//int qtDisponible = this.lcom.getProduit().getQuantite();
		int qtDisponible = this.produit.getQuantite();
		
		if(qtDemande <= qtDisponible){
			double prix = this.lcom.getProduit().getPrix();
			this.lcom.setPrix(prix*qtDemande);
			this.montantTotal = this.montantTotal + prix*qtDemande;
			this.listeLC.add(this.lcom);
			return "panier.xhtml";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la quantite maximale disponible est de: "+qtDisponible));
			return "voirProduitSeul.xhtml";
		}
		
	}
	
}
