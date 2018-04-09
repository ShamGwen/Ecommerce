package fr.adaming.managedBean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.ICommandeService;
import fr.adaming.Service.ILigneCommandeService;
import fr.adaming.Service.IProduitService;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Panier;
import fr.adaming.model.Produit;

@ManagedBean(name="panierMB")
@SessionScoped
public class PanierMB implements Serializable{
	@EJB
	private IProduitService prodServ;
	@EJB
	private ICommandeService comServ;
	
	//attributs
	private Panier panier;
	private HttpSession maSession;
	private LigneCommande lcom;
	private double montantTotal;
	private Produit produit;
	private List<LigneCommande> listeLC;
	private int quantite;

	//constructeur vide
	public PanierMB() {
		this.panier = new Panier();
		this.lcom = new LigneCommande();
		this.produit = new Produit();
	
	}
	
	//methode init
	@PostConstruct
	public void init() {
		//recuperer la session
		this.maSession = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
		//ajouter le panier dans la session
		maSession.setAttribute("panierSession", panier);
		//initialisation de la liste de lignes de commandes
		this.listeLC = new ArrayList<LigneCommande>();
		//enregistrement de la liste des lignes de commandes dans la session
		maSession.setAttribute("lcomListe", listeLC);
		//initialisation du montant total du panier
		montantTotal = 0;
		//enregistrement du montant total dans la session
		maSession.setAttribute("total", montantTotal);
		//intialisation de la quantite
		this.quantite = 0;
		//ajouter la quantite dans la session
		maSession.setAttribute("quantiteSession", quantite);
	}

	//Getters et setters
	public Panier getPanier() {
		return panier;
	}

	public void setPanier(Panier panier) {
		this.panier = panier;
	}

	public HttpSession getMaSession() {
		return maSession;
	}

	public void setMaSession(HttpSession maSession) {
		this.maSession = maSession;
	}

	public List<LigneCommande> getListeLC() {
		return listeLC;
	}

	public void setListeLC(List<LigneCommande> listeLC) {
		this.listeLC = listeLC;
	}
	
	public LigneCommande getLcom() {
		return lcom;
	}

	public void setLcom(LigneCommande lcom) {
		this.lcom = lcom;
	}

	
	public double getMontantTotal() {
		return montantTotal;
	}

	public void setMontantTotal(double montantTotal) {
		this.montantTotal = montantTotal;
	}

	public Produit getProduit() {
		return produit;
	}

	public void setProduit(Produit produit) {
		this.produit = produit;
	}

	public int getQuantite() {
		return quantite;
	}

	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}

	//Methodes metiers
	public String ajouterLCdansPanier(){
		
		//recuperer la quantite disponible (stock) du produit
		//Produit pOut = prodServ.rechercherProduitService(produit);
		//recuperer le produit dans la session
		Produit pOut = (Produit) maSession.getAttribute("produitSession");
		int qtDisponible = pOut.getQuantite();
		//verifier que la quantite demandee est disponible
		if(this.quantite<=qtDisponible){
			//creation d'une ligne de commande
			LigneCommande lcIn = new LigneCommande();
			lcIn.setProduit(pOut);
			lcIn.setQuantite(quantite);
			double prixLigne = pOut.getPrix()*quantite;
			lcIn.setPrix(prixLigne);
			//ajout de la ligne de commande dans la liste du panier
			this.listeLC.add(lcIn);
			//mise a jour de la liste dans la session
			maSession.setAttribute("lcomListe", listeLC);
			//modifier le montant total du panier et mise a jour dans la session
			this.montantTotal = montantTotal + prixLigne;
			maSession.setAttribute("total", montantTotal);
			return "panier.xhtml";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("la quantite maximale disponible est de: "+qtDisponible));
			return "voirProduitSeul.xhtml";
		}
		
	}
	
//	public String supprimerLCPanier(){
//		//recuperer la liste de ligne de commande dans la session
//		List<LigneCommande> lcOut = (List<LigneCommande>) maSession.getAttribute("lcomListe");
//		//supprimer la ligne de commande
//		 lcOut.remove(this.lcom);
//		//lcOut.remove(lcom.getIdLC());
//		//mise a jour dans la session
//		maSession.setAttribute("lcomListe", lcOut);
//		
//		
//		return "panier";
//	}
	
	
	public String annulerPanier(){
		//vider la liste dans la session
		maSession.setAttribute("lcomListe", null);
		//remettre le montant total a zero dans la session
		maSession.setAttribute("total", 0);
		return "accueil";
	}
	
	public String validerPanier(){
		//recuperer la liste de ligne de commande dans la session
		List<LigneCommande> lcOut = (List<LigneCommande>) maSession.getAttribute("lcomListe");
		
		//verifier que la liste n'est pas nulle
		if(lcOut!=null){
			//enregistrement de la commande
			Commande commandeIn = new Commande();
			commandeIn.setDateCommande(new Date());
			commandeIn.setListeLC(lcOut);
			//creation d'un client vide
			Client cl = new Client();
			//ajout de la commande dans la BDD et recuperation de son id
			Commande commandeOut = comServ.addCommande(commandeIn, cl);
			//enregistrement de la commande dans la session
			maSession.setAttribute("commandeSession", commandeOut);
			return "client.xhtml";
		}
		else{
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Vous ne pouvez pas valider si le panier est vide !!"));
			return "panier";
		}
		
	}
}
