package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import fr.adaming.Service.IAdminService;
import fr.adaming.model.Administrateur;
import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@ManagedBean(name = "adMB")
@RequestScoped
public class AdminMB implements Serializable {

	// attribut
	private Administrateur admin;
	private List<Produit> listeProduits;
	private List<Categorie> listeCategories;

	// transformation association uml en java

	@EJB
	private IAdminService adminServ;

	// constructeur
	public AdminMB() {
		this.admin = new Administrateur();
	}

	// get et set
	public Administrateur getAdmin() {
		return admin;
	}

	public void setAdmin(Administrateur admin) {
		this.admin = admin;
	}

	public List<Produit> getListeProduits() {
		return listeProduits;
	}

	public void setListeProduits(List<Produit> listeProduits) {
		this.listeProduits = listeProduits;
	}

	public List<Categorie> getListeCategories() {
		return listeCategories;
	}

	public void setListeCategories(List<Categorie> listeCategories) {
		this.listeCategories = listeCategories;
	}

	// methodes metiers

	public String seConnecter() {

		try {
			Administrateur adOut = adminServ.isExist(this.admin);

			return "succes";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("l'identifiant our le mdp n'exist pas"));
		}
		return "echec";
	}

}
