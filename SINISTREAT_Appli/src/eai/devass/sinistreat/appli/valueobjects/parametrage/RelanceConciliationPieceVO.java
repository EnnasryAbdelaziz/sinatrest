package eai.devass.sinistreat.appli.valueobjects.parametrage;

import eai.devass.sinistreat.appli.valueobjects.metier.instruction.PieceAtVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class RelanceConciliationPieceVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private String id;
	private String cocher;
	private PieceAtVO piece;

	public RelanceConciliationPieceVO() {
	}

	public RelanceConciliationPieceVO(String cocher, PieceAtVO piece) {
		this.cocher = cocher;
		this.piece = piece;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCocher() {
		return cocher;
	}

	public void setCocher(String cocher) {
		this.cocher = cocher;
	}

	public PieceAtVO getPiece() {
		return piece;
	}

	public void setPiece(PieceAtVO piece) {
		this.piece = piece;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}