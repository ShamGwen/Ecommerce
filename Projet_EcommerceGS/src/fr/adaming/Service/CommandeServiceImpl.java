package fr.adaming.Service;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import javax.ejb.EJB;
import javax.ejb.Stateful;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.CMYKColor;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import fr.adaming.Dao.ICommandeDao;
import fr.adaming.model.Client;
import fr.adaming.model.Commande;
import fr.adaming.model.LigneCommande;

@Stateful
public class CommandeServiceImpl implements ICommandeService {
	@EJB
	ICommandeDao comDao;

	@Override
	public List<Commande> getAllCommandesService(Client cl) {

		return comDao.getAllCommandesDao(cl);
	}

	@Override
	public Commande addCommande(Commande com, Client cl) {
		com.setClient(cl);
		return comDao.addCommande(com);
	}

	@Override
	public int deleteCommande(Commande com) {

		return comDao.deleteCommande(com);
	}

	@Override
	public void genererCommandePDF(Commande com,double montant) {
		//recuperer la liste des lignes de commande
		List<LigneCommande> liste = com.getListeLC();
		Document document = new Document(PageSize.A4, 75, 75, 75, 75);
		try {
			
			PdfWriter writer = PdfWriter.getInstance(document,
					new FileOutputStream("C:\\Users\\Public\\Commande_"+com.getIdCommande()+".pdf"));
			document.open();
			
			Paragraph titre = new Paragraph("ShamsGwen Makeup", FontFactory.getFont(FontFactory.HELVETICA_BOLD, 20, Font.UNDERLINE, new CMYKColor(0,36,0,0)));
			titre.setSpacingAfter(20);
			document.add(titre);
			
			Paragraph idCommande=new Paragraph("Commande "+String.valueOf(com.getIdCommande())+" datée du: "+com.getDateCommande(), FontFactory.getFont(FontFactory.HELVETICA_OBLIQUE,16));
			idCommande.setSpacingAfter(20);
			document.add(idCommande);
			
			Paragraph idcl = new Paragraph("Informations du client", FontFactory.getFont(FontFactory.HELVETICA, 14));
			idcl.setSpacingAfter(5);
			document.add(idcl);
			
			Paragraph nom = new Paragraph("Mme/Mr. "+com.getClient().getNomClient(), FontFactory.getFont(FontFactory.HELVETICA, 12));
			nom.setSpacingAfter(2);
			document.add(nom);
			Paragraph adresse = new Paragraph(com.getClient().getAdresse(), FontFactory.getFont(FontFactory.HELVETICA, 12));
			adresse.setSpacingAfter(2);
			document.add(adresse);
			Paragraph tel = new Paragraph(com.getClient().getTel(), FontFactory.getFont(FontFactory.HELVETICA, 12));
			tel.setSpacingAfter(2);
			document.add(tel);
			Paragraph mail = new Paragraph(com.getClient().getEmail(), FontFactory.getFont(FontFactory.HELVETICA, 12));
			mail.setSpacingAfter(20);
			document.add(mail);
			
			Paragraph articlest=new Paragraph("Liste des articles commandés:", FontFactory.getFont(FontFactory.HELVETICA,14));
			articlest.setSpacingAfter(5);
			document.add(articlest);
			
			PdfPTable listeArticles = new PdfPTable(5);
			listeArticles.setSpacingAfter(10);
			listeArticles.addCell("Reference");
			listeArticles.addCell("Description");
			listeArticles.addCell("PU");
			listeArticles.addCell("Quantite");
			listeArticles.addCell("Montant");
			
			for(LigneCommande lc : liste){
				listeArticles.addCell(lc.getProduit().getDesignation());
				listeArticles.addCell(lc.getProduit().getDescription());
				listeArticles.addCell(String.valueOf(lc.getProduit().getPrix()));
				listeArticles.addCell(String.valueOf(lc.getQuantite()));
				listeArticles.addCell(String.valueOf(lc.getPrix()));
				
			}
			document.add(listeArticles);
			
			Paragraph total = new Paragraph("Montant total de la commande: "+String.valueOf(montant)+" €", FontFactory.getFont(FontFactory.HELVETICA, 12));
			total.setSpacingAfter(10);
			document.add(total);
			
			
		} catch (FileNotFoundException | DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			document.close();
		}

	}

}
