package fr.adaming.Dao;

import javax.ejb.Local;

import fr.adaming.model.Client;

@Local
public interface IClientDao {
	
public Client ajouterClientDao(Client cl);

Client isExist(Client cl);


}
