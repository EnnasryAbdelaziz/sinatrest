package eai.devass.sinistreat.appli.modele.metier.reglement;

import eai.devass.sinistreat.appli.modele.parametrage.PieceReglement;

public class ReglementPieceAt {
	private long id;
	private Boolean cocher = Boolean.FALSE;
	private Reglement reglement;
	private PieceReglement pieceReglement;
	// Pour la convertion (VO BO)
	private transient String[] propertiesToConvert;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Boolean getCocher() {
		return cocher;
	}

	public void setCocher(Boolean cocher) {
		this.cocher = cocher;
	}

	



	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = new String[] { "id","cocher", "pieceReglement.libelle", "pieceReglement.code" };
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public Reglement getReglement() {
		return reglement;
	}

	public void setReglement(Reglement reglement) {
		this.reglement = reglement;
	}

	public PieceReglement getPieceReglement() {
		return pieceReglement;
	}

	public void setPieceReglement(PieceReglement pieceReglement) {
		this.pieceReglement = pieceReglement;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}


}

