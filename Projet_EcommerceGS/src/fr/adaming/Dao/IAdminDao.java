package fr.adaming.Dao;

import javax.ejb.Local;

import fr.adaming.model.Administrateur;

@Local
public interface IAdminDao {
	public Administrateur isExist(Administrateur admin);

}
