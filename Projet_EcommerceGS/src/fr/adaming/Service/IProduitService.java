package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {
	
	
	List<Produit> getAllProduitsService(Categorie cat);
}
