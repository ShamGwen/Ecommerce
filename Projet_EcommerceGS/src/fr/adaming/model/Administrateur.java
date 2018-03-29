package fr.adaming.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="administrateurs")
public class Administrateur implements Serializable{

	
	//Attributs
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id_admin")
	private int idAdmin;
	private String mail;
	private String mdp;
	
	//Constructeurs
	public Administrateur() {
		super();
	}
	
	public Administrateur(String mail, String mdp) {
		super();
		this.mail = mail;
		this.mdp = mdp;
	}
	
	public Administrateur(int idAdmin, String mail, String mdp) {
		super();
		this.idAdmin = idAdmin;
		this.mail = mail;
		this.mdp = mdp;
	}
	
	//Getters et setters
	public int getIdAdmin() {
		return idAdmin;
	}
	
	public void setIdAdmin(int idAdmin) {
		this.idAdmin = idAdmin;
	}
	
	public String getMail() {
		return mail;
	}
	
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	public String getMdp() {
		return mdp;
	}
	
	public void setMdp(String mdp) {
		this.mdp = mdp;
	}
	
	//Redefinition de la methode toString
	@Override
	public String toString() {
		return "Administrateur [idAdmin=" + idAdmin + ", mail=" + mail + ", mdp=" + mdp + "]";
	}
	
	
}
