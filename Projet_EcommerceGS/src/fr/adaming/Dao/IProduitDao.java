package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;
@Local
public interface IProduitDao {

	List<Produit> getAllProduitsDao(Categorie cat);
	
	
}
