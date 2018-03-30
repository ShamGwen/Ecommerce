package fr.adaming.managedBean;

import java.io.File;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.ICategorieService;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;

@ManagedBean(name = "catMB")
public class CategorieMB implements Serializable {

	// attributs
	private Categorie categorie;
	private HttpSession maSession;
	private List<Categorie> listeCat;

	
	//transformation de l'association UML en Java
	@EJB
	private ICategorieService catServ;

	// Constructeur vide
	public CategorieMB() {
		this.categorie = new Categorie();
	}

	@PostConstruct
	public void init() {
		//recuperer la liste des categories
		this.listeCat = catServ.getAllCategoriesService();
		
		//ajouter la liste des categories dans la session
		//FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("listeCat", this.listeCat);
	}

	// Getters et setters
	public Categorie getCategorie() {
		return categorie;
	}

	public void setCategorie(Categorie categorie) {
		this.categorie = categorie;
	}

	public List<Categorie> getListeCat() {
		return listeCat;
	}

	public void setListeCat(List<Categorie> listeCat) {
		this.listeCat = listeCat;
	}

	// Methodes metiers

	public String afficherImage(){
		//File fnew = new File()
		return null;
	}
	
//	public String ajouterCategorie(){
//		Client clAjout = clientService.addClientService(client, agent);
//		Categorie catOut = catServ.a
//
//		if (clAjout.getId() != 0) {
//			// recuperer la nouvelle liste
//			List<Client> liste = clientService.getAllClientsService(this.agent);
//			// mettre a jour dans la session
//			this.maSession.setAttribute("listeClients", liste);
//			return "accueil";
//		} else {
//			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Le client n'a pas été ajouté!!"));
//			return "ajouterClient";
//		}
//	}
}
