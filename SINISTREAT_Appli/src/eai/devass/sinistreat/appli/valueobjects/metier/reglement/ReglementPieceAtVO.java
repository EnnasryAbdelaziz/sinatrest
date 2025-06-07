package eai.devass.sinistreat.appli.valueobjects.metier.reglement;

import eai.devass.sinistreat.appli.valueobjects.parametrage.PieceReglementVO;
import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class ReglementPieceAtVO  implements IValueObject {
	private static final long serialVersionUID = 1L;
	private String id;
	private String cocher;
	// private InstructionVO instruction;
	private PieceReglementVO pieceReglement;

	public ReglementPieceAtVO() {
	}

	public ReglementPieceAtVO(String cocher, PieceReglementVO pieceReglement) {
		this.cocher = cocher;
		this.pieceReglement = pieceReglement;
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

	public PieceReglementVO getPieceReglement() {
		return pieceReglement;
	}

	public void setPieceReglement(PieceReglementVO pieceReglement) {
		this.pieceReglement = pieceReglement;
	}

}

	
