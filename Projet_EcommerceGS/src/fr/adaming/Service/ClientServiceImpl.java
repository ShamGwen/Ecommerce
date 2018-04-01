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
	public Client isExist(Client cl) {
		
		return clientDao.isExist(cl);
	}
	
	
	
	
}
