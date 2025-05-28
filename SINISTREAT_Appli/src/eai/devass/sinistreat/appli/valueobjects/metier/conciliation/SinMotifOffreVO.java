package eai.devass.sinistreat.appli.valueobjects.metier.conciliation;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;
import eai.devass.sinistreat.appli.valueobjects.parametrage.MotifOffreVO;

@SuppressWarnings("all")
public class SinMotifOffreVO implements IValueObject {

	private long id;
	private String dateEtat;
	private String userCreateur;
	private OffreVO refOffre;
	private MotifOffreVO refMotifOffre;
	private String codeSas;
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(String dateEtat) {
		this.dateEtat = dateEtat;
	}

	public String getUserCreateur() {
		return userCreateur;
	}

	public void setUserCreateur(String userCreateur) {
		this.userCreateur = userCreateur;
	}

	public OffreVO getRefOffre() {
		return refOffre;
	}

	public void setRefOffre(OffreVO refOffre) {
		this.refOffre = refOffre;
	}

	public MotifOffreVO getRefMotifOffre() {
		return refMotifOffre;
	}

	public void setRefMotifOffre(MotifOffreVO refMotifOffre) {
		this.refMotifOffre = refMotifOffre;
	}

	public String getCodeSas() {
		return codeSas;
	}

	public void setCodeSas(String codeSas) {
		this.codeSas = codeSas;
	}

}