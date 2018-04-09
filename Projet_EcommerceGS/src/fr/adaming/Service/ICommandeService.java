package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Local
public interface ICommandeService {
	
	
	public List<Commande> getAllCommandesService(Client cl);
    public Commande addCommande(Commande com,Client cl);
	public int deleteCommande(Commande com);

	public String genererCommandePDF(Commande com, double montant);
	
	
	
	
	
}
