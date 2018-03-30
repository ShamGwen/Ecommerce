package fr.adaming.managedBean;

import java.io.File;
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
import org.primefaces.model.UploadedFile;
import org.primefaces.model.UploadedFileWrapper;

import fr.adaming.Service.ICategorieService;
import fr.adaming.model.Categorie;
import fr.adaming.model.Client;

@ManagedBean(name = "catMB")
@RequestScoped
public class CategorieMB implements Serializable {

	// attributs
	private Categorie categorie;
	private HttpSession maSession;
	private List<Categorie> listeCat;
	private UploadedFile uf;
	

	
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
		maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		//ajouter la liste des categories dans la session
		maSession.setAttribute("listeCategorie", listeCat);
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

	public UploadedFile getUf() {
		return uf;
	}

	public void setUf(UploadedFile uf) {
		this.uf = uf;
	}

	// Methodes metiers
	public String afficherImage(){
		//File fnew = new File()
		return null;
	}
	
	public String ajouterCategorie(){
		//ajouter la photo dans l'objet a ajouter
		categorie.setPhoto(this.uf.getContents());
		
		Categorie catOut = catServ.addCategorieService(categorie);

		if (catOut.getIdCategorie() != 0) {
			// recuperer la nouvelle liste
			List<Categorie> liste = catServ.getAllCategoriesService();
			// mettre a jour
			this.listeCat = liste;
			
			return "accueilCategorie";
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La categorie n'a pas été ajoutée!!"));
			return "ajouterCategorie";
		}
	}
	
	public void modifierCategorie(RowEditEvent event){
		
		catServ.updateCategorieService((Categorie) event.getObject());
		List<Categorie> listeOut = catServ.getAllCategoriesService();
		maSession.setAttribute("listeCategorie", listeOut);
		
	}
	
	public String supprimerCategorie(){
		int verif = catServ.deleteCategorieService(categorie);
		if(verif != 0){
			List<Categorie> liste = catServ.getAllCategoriesService();
			this.listeCat = liste;
			return "accueilCategorie";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La catégorie n'a pas été supprimée!!"));
			return "supprimerCategorie";
		}
	}
}
