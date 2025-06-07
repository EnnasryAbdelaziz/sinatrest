package eai.devass.sinistreat.appli.modele.metier.conciliation;

import java.io.Serializable;
import java.util.List;

@SuppressWarnings("serial")
public class User implements Serializable{

	private long id;
	private String nom;
	private String prenom;
	private String codeSas;
	private List <UserDelegation> listUserDelegation;
	private Boolean isSuperieur;
	private Double seuilOperation;
	private transient String[] propertiesToConvert;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCodeSas() {
		return codeSas;
	}
	public void setCodeSas(String codeSas) {
		this.codeSas = codeSas;
	}
	public void setListUserDelegation(List <UserDelegation> listUserDelegation) {
		this.listUserDelegation = listUserDelegation;
	}
	public List <UserDelegation> getListUserDelegation() {
		return listUserDelegation;
	}
	public void setIsSuperieur(Boolean isSuperieur) {
		this.isSuperieur = isSuperieur;
	}
	public Boolean getIsSuperieur() {
		return isSuperieur;
	}
	public void setSeuilOperation(Double seuilOperation) {
		this.seuilOperation = seuilOperation;
	}
	public Double getSeuilOperation() {
		return seuilOperation;
	}
	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = propertiesToConvert.clone();
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}
	
}
