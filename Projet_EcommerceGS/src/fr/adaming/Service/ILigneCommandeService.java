package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.LigneCommande;
import fr.adaming.model.Produit;

@Local
public interface ILigneCommandeService {

	public List<LigneCommande> getAllLigneCommandesService(Produit prod);
    public LigneCommande addLigneCommandeService(LigneCommande lcom,Produit prod);
    public int deleteLigneCommandeService(LigneCommande lcom,Produit prod);
}
