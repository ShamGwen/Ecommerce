package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
   public interface ICommandeDao {

	public List<Commande> getAllCommandesDao(Client cl);
	public Commande addCommande(Commande com);
	public int deleteCommande(Commande com);
	
	
	

}
