package fr.adaming.Service;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import fr.adaming.Dao.IAdminDao;
import fr.adaming.model.Administrateur;

@Stateful
public class AdminServiceImpl implements IAdminService {
	
	@EJB
	private IAdminDao adminDao;

	@Override
	public Administrateur isExist(Administrateur admin) {


		return adminDao.isExist(admin);
	}

}
