package eai.devass.gsr.appli.valueobjects.parametrage;

import eai.devass.commun.appli.converter.AConverter;

/**
 * Value Object de EtatRentier
 * 
 * @author Nom Prenom (email)
 */
@AConverter(entiteDist="eai.devass.gsr.appli.modele.parametrage.MvtEtatRentier")
public class MvtEtatRentierVO extends ParamVO {
	private static final long serialVersionUID = 1L;

	@AConverter(propertyDist="typeEtat")
	private String typeEtat;

	/**
	 * @return the typeEtat
	 */
	public String getTypeEtat() {
		return typeEtat;
	}

	/**
	 * @param typeEtat the typeEtat to set
	 */
	public void setTypeEtat(String typeEtat) {
		this.typeEtat = typeEtat;
	}
	

}
