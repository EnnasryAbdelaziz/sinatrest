package eai.devass.sinistreat.appli.modele.metier.instruction;

public class InstructionRejetAt {
	private long id;
	private Boolean cocher = Boolean.FALSE;
	private Instruction instruction;
	private RejetAt rejetAt;
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

	public RejetAt getRejetAt() {
		return rejetAt;
	}

	public void setRejetAt(RejetAt rejetAt) {
		this.rejetAt = rejetAt;
	}

	public void setPropertiesToConvert(String[] propertiesToConvert) {

		String[] copyPropertiesToConvert = null;
		if (propertiesToConvert != null) {
			copyPropertiesToConvert = new String[] { "id","cocher", "rejetAt.libelle", "rejetAt.code" };
		}

		this.propertiesToConvert = copyPropertiesToConvert;
	}

	public String[] getPropertiesToConvert() {
		return propertiesToConvert;
	}

	

}