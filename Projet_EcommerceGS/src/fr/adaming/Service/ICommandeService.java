package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Client;
import fr.adaming.model.Commande;

@Local
public interface ICommandeService {
	
	
	public List<Commande> getAllCommandesService(Client cl);

}
