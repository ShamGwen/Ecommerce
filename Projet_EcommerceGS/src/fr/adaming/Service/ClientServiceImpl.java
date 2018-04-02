package fr.adaming.Service;

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
		Client clOut = clientDao.isExist(cl);
		if(clOut==null){
			return clientDao.ajouterClientDao(cl);
		}
		else{
			return clOut;
		}
		
	}

	
	
	
	
	
}
