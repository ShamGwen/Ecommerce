package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import org.primefaces.event.RowEditEvent;
import org.primefaces.model.UploadedFile;

import fr.adaming.Service.IProduitService;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;
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
	private Boolean indice;
	private UploadedFile uf;
	private String motCle;

	// constructeur vide
	public ProduitMB() {
		this.produit = new Produit();
		this.categorie = new Categorie();
		this.indice = false;
	}

	@PostConstruct
	public void init() {
		// this.categorie = (Categorie)
		// maSession.getAttribute("categorieSession");
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.listeProduits = prodService.getAllProduitsService();

	}

	// get set
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

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	public String getMotCle() {
		return motCle;
	}

	public void setMotCle(String motCle) {
		this.motCle = motCle;
	}

	// methodes metier
	public String ajouterProduit() {
		produit.setPhoto(this.uf.getContents());
		Produit prodAjout = prodService.ajouterProduitService(this.produit, this.categorie);
		if (prodAjout.getIdProduit() != 0) {

			// recuperer la liste de clients
			List<Produit> liste = prodService.getAllProduitsService();

			// metre a jour la liste dans la liste
			this.listeProduits = liste;
			return "accueilProduit";
		} else {

			// le message en cas dechec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout de produit est echoue"));
			return "ajouterProduit";

		}
	}

	public String deleteProduit() {
		int verif = prodService.deleteProduitService(this.produit);
		if (verif != 0) {
			// recuperer la liste de clients
			List<Produit> liste = prodService.getAllProduitsService();

			// mettre a jour la lisste dans la session
			this.listeProduits = liste;
			return "accueilProduit";
		} else {
			// le messag een cas dechec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le produit n'est pas supprimé"));
			return "deleteProduit";
		}
	}

	public String modifierProduit() {
		produit.setPhoto(this.uf.getContents());
		int verif = prodService.updateProduitService(this.produit, this.categorie);
		
		if (verif != 0) {
			// recuperer la liste de clients
			List<Produit> liste = prodService.getAllProduitsService();

			// metre a jour la liste dans la liste
			this.listeProduits = liste;
			return "accueilProduit";
		} else {
			// le messag een cas dechec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le produit n'est pas modifier"));
			return "modifierProduit";
		}

	}

	public String afficherListeProduitsSpecifiques() {
		// mettre a jour la liste des produits
		List<Produit> liste = prodService.getAllProduitsService(categorie);
		this.listeProduits = liste;
		return "voirProduitCategorie";
	}
	
	public String afficherFicheProduit(){
		return "voirProduitSeul";
	}

	public String rechercherMotCle() {
		List<Produit> listeRech = prodService.getProduitsRechService(motCle);
		this.listeProduits = listeRech;

		return "produitsRecherches.xhtml";

	}

	// public String findProduit() {
	// try {
	// this.produit = prodService.rechercherProduitService(this.produit,
	// this.categorie);
	// this.indice = true;
	//
	// } catch (Exception ex) {
	//
	// // le message en cas dechec
	// FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("le
	// produit chercher n'exist pas "));
	// this.indice = false;
	//
	// }
	// return "rechercherProduit";
	// }
	//
	//

}