package eai.devass.sinistreat.appli.modele.parametrage;

import eai.devass.sinistreat.appli.modele.metier.conciliation.RelanceConciliation;
import eai.devass.sinistreat.appli.modele.metier.instruction.PieceAt;

public class RelanceConciliationPiece {
	private long id;
	private Boolean cocher = Boolean.FALSE;
	private RelanceConciliation relanceConciliation;
	private PieceAt piece;
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

	public RelanceConciliation getRelanceConciliation() {
		return relanceConciliation;
	}

	public void setRelanceConciliation(RelanceConciliation relanceConciliation) {
		this.relanceConciliation = relanceConciliation;
	}

	public PieceAt getPiece() {
		return piece;
	}

	public void setPiece(PieceAt piece) {
		this.piece = piece;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = new String[] { "id","cocher", "piece.libelle", "piece.code" };
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

}