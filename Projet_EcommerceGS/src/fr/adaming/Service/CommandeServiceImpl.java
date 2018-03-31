package fr.adaming.Service;

import java.util.List;

import javax.ejb.EJB;

import fr.adaming.Dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;

public class CommandeServiceImpl implements ICommandeService {
	@EJB
	ICommandeDao comDao;

	@Override
	public List<Commande> getAllCommandesService(Client cl) {
		
	return comDao.getAllCommandesDao(cl);
	}

}
