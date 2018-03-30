package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;

import fr.adaming.Service.IProduitService;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
import fr.adaming.model.Produit;

@ManagedBean(name = "prodMB")
public class ProduitMB implements Serializable {
	// transformation uml en java
	@EJB
	IProduitService prodService;

	// declaration des attributs pour envoyer a la page
	private Produit produit;
	private Categorie categorie;
	private HttpSession maSession;
	private List<Produit> listeProduits;
	private Boolean indice;

	// constructeur vide
	public ProduitMB() {
		this.produit = new Produit();
		this.indice = false;
	}

	@PostConstruct
	public void init() {
		this.categorie = (Categorie) maSession.getAttribute("categorieSession");
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listeProduits = prodService.getAllProduitsService(this.categorie);

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

	public Boolean getIndice() {
		return indice;
	}

	public void setIndice(Boolean indice) {
		this.indice = indice;
	}

	// methodes metier
	public String ajouterProduit() {

		Produit prodAjout = prodService.ajouterProduitService(this.produit, this.categorie);
		if (prodAjout.getIdProduit() != 0) {

			// recuperer la liste de clients
			List<Produit> liste = prodService.getAllProduitsService(this.categorie);

			// metre a jour la liste dans la session
			maSession.setAttribute("listeProduits", liste);
			return "accueilProduit";
		} else {

			// le message en cas dechec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout de produit est echoue"));
			return "ajouterProduit";

		}
	}

	//public String modifierproduit() {

		//Produit prodModif = prodService.updateProduitService(this.produit, this.categorie);
		//if (prodModif.getIdProduit() != 0) {
			// recuperer la liste de clients
			//List<Produit> liste = prodService.getAllProduitsService(this.categorie);

			// mettre a jour la lisste dans la session
			//maSession.setAttribute("listeProduits", liste);
			//return "accueilProduit";
		//} else {
			// le messag een cas dechec
			//FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("laclient n'est pas modifier"));
			//return "modifierProduit";
		//}
	//}

	public String deleteClient() {
		Produit proDel = prodService.deleteProduitService(this.produit, this.categorie);
		if (proDel != null) {
			// recuperer la liste de clients
			List<Produit> liste = prodService.getAllProduitsService(this.categorie);

			// mettre a jour la lisste dans la session
			maSession.setAttribute("listeProduits", liste);
			return "accueilProduit";
		} else {
			// le messag een cas dechec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le client est pas supprimer"));
			return "deleteProduit";
		}
	}

	public String findProduit() {
		try {
			this.produit = prodService.rechercherProduitService(this.produit, this.categorie);
			this.indice = true;

		} catch (Exception ex) {

			// le message en cas dechec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le produit chercher n'exist pas "));
			this.indice = false;

		}
		return "rechercherProduit";
	}

	public void modifierProduit(RowEditEvent event) {

		Produit prodModif = prodService.updateProduitService((Produit) event.getObject());
		List<Produit> liste = prodService.getAllProduitsService(this.categorie);
		maSession.setAttribute("listeProduits", liste);

	}
}