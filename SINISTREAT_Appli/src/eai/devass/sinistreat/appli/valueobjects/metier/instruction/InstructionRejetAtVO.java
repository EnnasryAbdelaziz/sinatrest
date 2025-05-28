package eai.devass.sinistreat.appli.valueobjects.metier.instruction;

import ma.co.omnidata.framework.services.businessInterface.IValueObject;

public class InstructionRejetAtVO implements IValueObject {
	private static final long serialVersionUID = 1L;
	private String id;
	private String cocher;
	// private InstructionVO instruction;
	private RejetAtVO rejetAt;

	public InstructionRejetAtVO() {
	}

	public InstructionRejetAtVO(String cocher, RejetAtVO rejetAt) {
		this.cocher = cocher;
		this.rejetAt = rejetAt;
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

	public RejetAtVO getRejetAt() {
		return rejetAt;
	}

	public void setRejetAt(RejetAtVO rejetAt) {
		this.rejetAt = rejetAt;
	}

}