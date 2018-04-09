package fr.adaming.Service;

import java.util.Properties;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.Dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
public class ClientServiceImpl implements IClientService {
	@EJB
	private IClientDao clientDao;

	@Override
	public Client recupererClientService(Client cl) {
		// verifier si le client existe deja dans la BDD sinon en creer un nouveau
		try {
			Client clOut = clientDao.isExist(cl);
			return clOut;
		} catch (Exception e) {
			return clientDao.ajouterClientDao(cl);
		}

		
		
	}


	
}
