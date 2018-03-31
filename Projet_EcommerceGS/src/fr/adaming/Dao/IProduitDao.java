package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Local;

import org.primefaces.event.RowEditEvent;

import fr.adaming.model.Categorie;
import fr.adaming.model.Produit;

@Local
public interface IProduitDao {

	public List<Produit> getAllProduitsDao(Categorie cat);

	public Produit ajouterProduitDao(Produit prod);

	public int updateProduitDao(Produit prod);

	public int deleteProduitDao(Produit prod);

	public Produit rechercherProduitDao(Produit prod);

	public List<Produit> getAllProduitsDao();

}
