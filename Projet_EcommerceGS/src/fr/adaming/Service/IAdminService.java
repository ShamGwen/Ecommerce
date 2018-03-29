package fr.adaming.Service;

import javax.ejb.Local;

import fr.adaming.model.Administrateur;

@Local
public interface IAdminService {
	public Administrateur isExist(Administrateur admin);

}
