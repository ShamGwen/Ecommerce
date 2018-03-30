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

	public Produit updateProduitService(Produit prod);

	public Produit deleteProduitService(Produit prod, Categorie cat);

	public Produit rechercherProduitService(Produit prod, Categorie cat);

	Produit updateProduitService(Produit prod, Categorie cat);

	public List<Produit> getAllProduitsService();

}
