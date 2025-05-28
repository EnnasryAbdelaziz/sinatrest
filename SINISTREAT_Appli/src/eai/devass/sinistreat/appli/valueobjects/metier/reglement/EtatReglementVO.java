package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.EtatRglVO;


public class EtatReglementVO implements IValueObject {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Long id;
	private String motifEtat;
	private String dateEtat;
	private String utilisateurCreateur;
	private EtatRglVO refEtat;
	private ReglementVO refReglement;
	private String reference;
	private String modeReglement;
	
	public EtatReglementVO() {
		
	}
	public EtatReglementVO(EtatRglVO refEtat) {
		this.refEtat = refEtat;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMotifEtat() {
		return motifEtat;
	}
	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}
	public String getDateEtat() {
		return dateEtat;
	}
	
	public String getUtilisateurCreateur() {
		return utilisateurCreateur;
	}
	public void setUtilisateurCreateur(String utilisateurCreateur) {
		this.utilisateurCreateur = utilisateurCreateur;
	}
	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}
	public EtatRglVO getRefEtat() {
		return refEtat;
	}
	public void setRefEtat(EtatRglVO refEtat) {
		this.refEtat = refEtat;
	}
	public ReglementVO getRefReglement() {
		return refReglement;
	}
	public void setRefReglement(ReglementVO refReglement) {
		this.refReglement = refReglement;
	}
	public String getReference() {
		return reference;
	}
	public void setReference(String reference) {
		this.reference = reference;
	}
	public String getModeReglement() {
		return modeReglement;
	}
	public void setModeReglement(String modeReglement) {
		this.modeReglement = modeReglement;
	}



}