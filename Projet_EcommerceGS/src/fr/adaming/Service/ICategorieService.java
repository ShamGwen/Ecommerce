package fr.adaming.Service;

import java.util.List;

import javax.ejb.Local;

import fr.adaming.model.Categorie;

@Local
public interface ICategorieService {

	public List<Categorie> getAllCategoriesService();

	public Categorie addCategorieService(Categorie cat);

	public Categorie updateCategorieService(Categorie cat);

	public Categorie deleteCategorieService(Categorie cat);

	public Categorie getCategorieByIdService(Categorie cat);
}
