package eai.devass.sinistreat.appli.modele.metier.instruction;

public class InstructionPieceAt {
	private long id;
	private Boolean cocher = Boolean.FALSE;
	private Instruction instruction;
	private PieceAt pieceAt;
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

	public Instruction getInstruction() {
		return instruction;
	}

	public void setInstruction(Instruction instruction) {
		this.instruction = instruction;
	}

	public PieceAt getPieceAt() {
		return pieceAt;
	}

	public void setPieceAt(PieceAt pieceAt) {
		this.pieceAt = pieceAt;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = new String[] { "id","cocher", "pieceAt.libelle", "pieceAt.code" };
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

}