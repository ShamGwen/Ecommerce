package fr.adaming.Service;

import javax.ejb.Local;

import fr.adaming.model.Client;
@Local
public interface IClientService {
	
	public Client recupererClientService(Client cl);

}
