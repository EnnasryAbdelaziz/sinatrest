package eai.devass.sinistreat.appli.valueobjects.metier.sinistre;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatSinVO;


public class EtatSinistreVO implements IValueObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String id;
	private String motifEtat;
	private String dateEtat;
	private EtatSinVO refEtat;
	private SinistreVO refSinistre;	
	private String userCreateur;
	
	public EtatSinistreVO() {
		
	}
	public EtatSinistreVO(EtatSinVO refEtat) {
		this.refEtat = refEtat;
	}
	public String getUserCreateur() {
		return userCreateur;
	}
	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}
	public String getMotifEtat() {
		return motifEtat;
	}
	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}

	public EtatSinVO getRefEtat() {
		return refEtat;
	}
	public void setRefEtat(EtatSinVO refEtat) {
		this.refEtat = refEtat;
	}
	public SinistreVO getRefSinistre() {
		return refSinistre;
	}
	public void setRefSinistre(SinistreVO refSinistre) {
		this.refSinistre = refSinistre;
	}
	public String getDateEtat() {
		return dateEtat;
	}
	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getId() {
		return id;
	}
}