package fr.adaming.managedBean;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.ICommandeService;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;


@ManagedBean(name="comMB")
@RequestScoped
public class CommandeMB implements Serializable{
 //transformation l'association uml en java 
	
	@EJB
	ICommandeService commandeService;	
	
	
	//declaration des attributs envoyer a la page
	private Commande commande;
	private Client client;
	private HttpSession maSession;
	
	
	//constructeur vide
	public CommandeMB() {
		this.commande=new Commande();
	}
	
	@PostConstruct
	public void init(){
		this.maSession=(HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	
	//recuperer le client stocker dans la session
		this.client=(Client) maSession.getAttribute("clientsession");
		
	
		
		
	//get et set	
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
	
	
	
	
	
}
