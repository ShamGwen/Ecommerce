package fr.adaming.Dao;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Local
public interface ILigneCommandeDao {

	public List<LigneCommande> getAllLigneCommandesDao(Produit prod);
	public LigneCommande addLigneCommande(LigneCommande lcom);
	public int deleteLigneCommande(LigneCommande lcom);
}
