package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.List;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

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

	private Client client;
	private List<Commande> listeCommandes;

	// constructeur vide
	public ClientMB() {
		this.client = new Client();
	}

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
	public String seConnecter() {
		try {
			Client clOut = clientService.isExist(this.client);
			this.listeCommandes = commandeService.getAllCommandesService(clOut);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("clientSession", clOut);

			FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("commandesliste",
					this.listeCommandes);
			return "succes";
		} catch (Exception ex) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage("l'identifiant our le mdp n'exist pas"));
		}
		return "echec";
	}

}
