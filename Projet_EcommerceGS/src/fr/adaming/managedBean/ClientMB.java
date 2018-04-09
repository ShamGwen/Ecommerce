package fr.adaming.managedBean;

import java.io.File;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.http.HttpSession;

import fr.adaming.Service.IClientService;
import fr.adaming.Service.ICommandeService;
import fr.adaming.Service.IProduitService;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@ManagedBean(name = "clMB")
@RequestScoped

public class ClientMB implements Serializable {
	// transformation uml en java

	@EJB
	private IClientService clientService;
	@EJB
	private ICommandeService commandeService;
	@EJB
	private IProduitService produitService;

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

	// Getters et setters
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

	public String seConnecter() {
		// recuperer l'ancien ou le nouveau client
		Client clOut = clientService.recupererClientService(client);
		if (clOut != null) {

			// recuperer la liste de ligne de commande dans la session
			List<LigneCommande> lcOut = (List<LigneCommande>) maSession.getAttribute("lcomListe");
			// enregistrement de la commande
			Commande commandeIn = new Commande();
			commandeIn.setDateCommande(new Date());
			commandeIn.setListeLC(lcOut);
			// ajout de la commande dans la BDD et recuperation de son id
			Commande commandeOut = commandeService.addCommande(commandeIn, clOut);

			// recuperer le montant total de la commande
			double total = (double) maSession.getAttribute("total");

			// modifier la qt restante de chaque produit dans la BDD
			for (LigneCommande lc : lcOut) {
				// pour chaque produit (ligne de commande) on recupere dans la
				// BDD sa quantite disponible et la modifie en enlevant la
				// quantite selectionnee
				int qtDemandee = lc.getQuantite();
				Produit prodModif = lc.getProduit();
				int qtDispo = prodModif.getQuantite();
				prodModif.setQuantite(qtDispo - qtDemandee);
				produitService.updateProduitService(prodModif, prodModif.getCategorie());
			}

			// generer la commande en pdf
			String cheminPDF = commandeService.genererCommandePDF(commandeOut, total);

			// envoyer par mail la commande en PDF
			String username = "gwenaelle.baldit@outlook.fr";
			String password = "gwenaelle.15";

			Properties props = new Properties();
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.starttls.enable", "true");
			props.put("mail.smtp.host", "smtp-mail.outlook.com");
			props.put("mail.smtp.port", "587");

			// Get Session object.
			Session session = Session.getInstance(props, new Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			});
			try {

				// Create a default MimeMessage object.
				Message message = new MimeMessage(session);
				Multipart multipart = new MimeMultipart();

				// Set From: header field of the header.
				message.setFrom(new InternetAddress(username));

				// Set To: header field of the header.
				message.addRecipient(Message.RecipientType.TO, new InternetAddress(clOut.getEmail()));

				// Set Subject: header field
				message.setSubject("Vaildation de votre commande chez ShamsGwen Makeup");

				MimeBodyPart messageBodyPart = new MimeBodyPart();
				messageBodyPart.setContent(
						"Bonjour,<br/>Vous trouverez ci-joint votre commande qui sera expédiée dans les plus brefs délais."
						+ "Merci de nous faire confiance !<br/><br/>Bien cordialement,<br/>Le service client, ShamsGwen Makeup.",
						"text/html");

				// creates body part for the attachment
				MimeBodyPart attachPart = new MimeBodyPart();
				String attachFile = cheminPDF;

				DataSource source = new FileDataSource(attachFile);
				attachPart.setDataHandler(new DataHandler(source));
				attachPart.setFileName(new File(attachFile).getName());

				// adds parts to the multipart
				multipart.addBodyPart(messageBodyPart);
				multipart.addBodyPart(attachPart);

				// sets the multipart as message's content
				message.setContent(multipart);

				// Send message
				Transport.send(message, message.getAllRecipients());
				System.out.println("Sent message successfully....");
			} catch (MessagingException mex) {
				mex.printStackTrace();
			}
			finally {
				//vider le panier et remettre a zero le montant total
				//vider la liste dans la session
				maSession.setAttribute("lcomListe", null);
				//remettre le montant total a zero dans la session
				maSession.setAttribute("total", 0);
			}
		}

		return "accueil";
	}	


	public String annulerIdentification() {
		// annuler la commande
		maSession.setAttribute("commandeSession", null);
		return "panier";
	}
}
