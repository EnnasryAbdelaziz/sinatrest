package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import java.util.List;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

@SuppressWarnings("serial")
public class UserVO  implements IValueObject{

	private long id;
	private String nom;
	private String prenom;
	private String codeSas;
	private List<UserDelegationVO> listUserDelegation;
	private String isSuperieur;
	private String seuilOperation;
	
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

	public void setListUserDelegation(List<UserDelegationVO> listUserDelegation) {
		this.listUserDelegation = listUserDelegation;
	}

	public List<UserDelegationVO> getListUserDelegation() {
		return listUserDelegation;
	}

	public void setIsSuperieur(String isSuperieur) {
		this.isSuperieur = isSuperieur;
	}

	public String getIsSuperieur() {
		return isSuperieur;
	}

	public void setSeuilOperation(String seuilOperation) {
		this.seuilOperation = seuilOperation;
	}

	public String getSeuilOperation() {
		return seuilOperation;
	}

}
