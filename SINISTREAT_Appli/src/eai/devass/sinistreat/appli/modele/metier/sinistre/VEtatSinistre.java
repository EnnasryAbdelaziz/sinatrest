package eai.devass.sinistreat.appli.modele.metier.sinistre;

import java.util.Date;

import eai.devass.sinistreat.appli.modele.parametrage.EtatSin;

public class VEtatSinistre {
	
	private Long id;
	private EtatSin refEtat;
	private Sinistre refSinistre;
	private String motifEtat;
	private Date dateEtat;
	
	public VEtatSinistre() {
		
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}



	

	public EtatSin getRefEtat() {
		return refEtat;
	}

	public void setRefEtat(EtatSin refEtat) {
		this.refEtat = refEtat;
	}

	public Sinistre getRefSinistre() {
		return refSinistre;
	}

	public void setRefSinistre(Sinistre refSinistre) {
		this.refSinistre = refSinistre;
	}

	public String getMotifEtat() {
		return motifEtat;
	}

	public void setMotifEtat(String motifEtat) {
		this.motifEtat = motifEtat;
	}

	public Date getDateEtat() {
		return dateEtat;
	}

	public void setDateEtat(Date dateEtat) {
		this.dateEtat = dateEtat;
	}
	
	
	
}