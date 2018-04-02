package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import org.primefaces.event.RowEditEvent;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitService {

	public List<Produit> getAllProduitsService(Categorie cat);

	public Produit ajouterProduitService(Produit prod, Categorie cat);

	public int deleteProduitService(Produit prod);

	public Produit rechercherProduitService(Produit prod);

	public int updateProduitService(Produit prod,Categorie cat);

	public List<Produit> getAllProduitsService();

	public List<Produit> getProduitsRechService(String motCle);
	

}
