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
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@ManagedBean(name ="comMB")
@RequestScoped
public class CommandeMB implements Serializable {
	// transformation l'association uml en java

	@EJB
	ICommandeService commandeService;

	// declaration des attributs envoyer a la page
	private Commande commande;
	private Client client;
	private HttpSession maSession;
	private List<Commande> listeCommandes;

	// constructeur vide
	public CommandeMB() {
		this.commande = new Commande();
	}

	@PostConstruct
	public void init() {
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);

		// recuperer le client stocker dans la session
		this.client = (Client) maSession.getAttribute("clientsession");

		// get et set
	}

	public Commande getCommande() {
		return commande;
	}

	public void setCommande(Commande commande) {
		this.commande = commande;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public List<Commande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	// methodes metiers
	public String ajouterCommande(){
	Commande comAjout=commandeService.addCommande(this.commande,this.client);
	 if(comAjout.getIdCommande()!=0){
	//recuperer la liste de commandes 
		List<Commande> liste=commandeService.getAllCommandesService(this.client);
	// metre a jour la liste dans la session
		this.listeCommandes=liste;
			return"accueilCommande";
		} else {

			// le message en cas echec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout de client est echoue"));
			return "ajouterCommande";
		
	}
	}
	 public String deleteCommande(){
		

	 int verif=commandeService.deleteCommande(this.commande);
	 if(verif !=0){
	 //recupere la lsite de la commandes
	 
	List<Commande> liste=commandeService.getAllCommandesService(this.client); 

	// metre a jour la liste dans la session
		this.listeCommandes=liste;
			return"accueilCommande";
		} else {

			// le message en cas echec
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("l'ajout de client est echoue"));
			return "supprimerCommande";
		 
	 }

}
}