package eai.devass.sinistreat.appli.valueobjects.metier.instruction;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InstructionPieceAtVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private String id;
	private String cocher;
	// private InstructionVO instruction;
	private PieceAtVO pieceAt;

	public InstructionPieceAtVO() {
	}

	public InstructionPieceAtVO(String cocher, PieceAtVO pieceAt) {
		this.cocher = cocher;
		this.pieceAt = pieceAt;
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

	// public InstructionVO getInstruction() {
	// return instruction;
	// }
	//
	// public void setInstruction(InstructionVO instruction) {
	// this.instruction = instruction;
	// }

	public PieceAtVO getPieceAt() {
		return pieceAt;
	}

	public void setPieceAt(PieceAtVO pieceAt) {
		this.pieceAt = pieceAt;
	}

}