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

import fr.adaming.Service.ICommandeService;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@ManagedBean(name = "lcomMB")
@RequestScoped
public class LigneCommandeMB implements Serializable {

	@EJB
	ICommandeService lignecommandeService;

	// declaration des attriburs envoyer a la page
	private LigneCommande lignecommande;
	private Produit produit;
	private HttpSession maSession;
	private List<LigneCommande> listeligneCommandes;

	// constructeur vide
	public LigneCommandeMB() {
		this.lignecommande = new LigneCommande();
	}

	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		this.produit = (Produit) maSession.getAttribute("lignecommandesession");
	}

	// get et set
	public LigneCommande getLignecommande() {
		return lignecommande;
	}

	public void setLignecommande(LigneCommande lignecommande) {
		this.lignecommande = lignecommande;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public List<LigneCommande> getListeligneCommandes() {
		return listeligneCommandes;
	}

	public void setListeligneCommandes(List<LigneCommande> listeligneCommandes) {
		this.listeligneCommandes = listeligneCommandes;
	}

//	// methodes metier
//	public String ajouterLigneCommande() {
//		LigneCommande lcomAjout = lignecommandeService.addLigneCommandeService(this.lignecommande);
//		if (lcomAjout.getIdLC() != 0) {
//			// recuperre la liste de commandes
//			List<LigneCommande> liste = lignecommandeService.getAllLigneCommandesService(this.produit);
//			// mettre a jour la liste
//			this.listeligneCommandes = liste;
//			return "ligneCommande";
//		} else {
//
//			// le message en cas echec
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout de client est echoue"));
//			return "ajouterLigneCommande";
//
//		}
//
//	}
//
//	public String deleteLigneCommande() {
//		int verif = lignecommandeService.deleteLigneCommandeService(this.lignecommande, this.produit);
//		if (verif != 0) {
//			List<LigneCommande> liste = lignecommandeService.getAllLigneCommandesService(this.produit);
//			// mettre a jour la liste
//			this.listeligneCommandes = liste;
//			return "ligneCommande";
//		} else {
//
//			// le message en cas echec
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout de client est echoue"));
//			return "supprimerLigneCommande";
//
//		}
//
//	}

}
