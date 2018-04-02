package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.IClientService;
import fr.adaming.Service.ICommandeService;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@ManagedBean(name = "clMB")
@RequestScoped

public class ClientMB implements Serializable {
	// transformation uml en java

	@EJB
	private IClientService clientService;
	@EJB
	private ICommandeService commandeService;

	// attributs
	private Client client;
	private List<Commande> listeCommandes;
	private HttpSession maSession;

	// constructeur vide
	public ClientMB() {
		this.client = new Client();
	}

	// methode init
	@PostConstruct
	public void init() {
		// recuperer la session
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	}

	//Getters et setters
	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public List<Commande> getListeCommandes() {
		return listeCommandes;
	}

	public void setListeCommandes(List<Commande> listeCommandes) {
		this.listeCommandes = listeCommandes;
	}

	// methode seConnecter
	// public String seConnecter() {
	// try {
	// Client clOut = clientService.isExist(this.client);
	// this.listeCommandes = commandeService.getAllCommandesService(clOut);
	//
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientSession",
	// clOut);
	//
	// FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("commandesliste",
	// this.listeCommandes);
	// return "succes";
	// } catch (Exception ex) {
	// FacesContext.getCurrentInstance().addMessage(null,
	// new FacesMessage("l'identifiant our le mdp n'exist pas"));
	// }
	// return "echec";
	// }

	public String seConnecter(){
		//recuperer l'ancien ou le nouveau client
		Client clOut = clientService.recupererClientService(client);
		// recuperer, modifier et enregistrer la commande dans la session
		Commande com = (Commande) maSession.getAttribute("commandeSession");
		if(com!=null){
			com.setClient(clOut);
			maSession.setAttribute("commandeSession", com);
			//recuperer le montant total de la commande
			double total = (double) maSession.getAttribute("total");
			
			//modifier la qt restante de chaque produit dans la BDD **********************************************
			
			//generer la commande en pdf
			commandeService.genererCommandePDF(com, total);
			
			return "accueil";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("La commande n'est pas valide"));
			return "panier";
		}
		
		
	}
	
	public String annulerIdentification(){
		//annuler la commande
		maSession.setAttribute("commandeSession", null);
		return "panier";
	}
}
