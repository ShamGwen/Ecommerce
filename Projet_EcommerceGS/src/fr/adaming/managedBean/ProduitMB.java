package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.IProduitService;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@ManagedBean(name = "prodMB")
@RequestScoped
public class ProduitMB implements Serializable {
	// transformation uml en java
	@EJB
	IProduitService prodService;

	// declaration des attributs pour envoyer a la page
	private Produit produit;
	private Categorie categorie;
	private HttpSession maSession;
	private List<Produit> listeProduits;

	// constructeur vide
	public ProduitMB() {
		this.produit = new Produit();

	}

	@PostConstruct
	public void init() {
		this.categorie = (Categorie) maSession.getAttribute("categorieSession");
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listeProduits=prodService.getAllProduitsService(this.categorie);
		
	}

	public ProduitMB(IProduitService prodService, Produit produit, Categorie categorie, HttpSession maSession) {
		super();
		this.prodService = prodService;
		this.produit = produit;
		this.categorie = categorie;
		this.maSession = maSession;
	}

	public IProduitService getProdService() {
		return prodService;
	}

	public void setProdService(IProduitService prodService) {
		this.prodService = prodService;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

}
