package fr.adaming.Service;

import javax.ejb.Stateful;

import fr.adaming.Dao.IClientDao;
import fr.adaming.model.Client;

@Stateful
   public class ClientServiceImpl implements IClientService {

	private IClientDao clientDao;

	@Override
	public Client isExist(Client cl) {
		
		return clientDao.isExist(cl);
	}
	
	
	
	
}
