package eai.devass.edition.Utils.beans;

public class ReportParameterBean {
	private String name;
	private String type;
	private String libelle;
	private String pattern;
	private String textAlignment;
	
	public ReportParameterBean(String name, String type, String libelle, String pattern, String textAlignment) {
		this.name = name;
		this.type = type;
		this.libelle = libelle;
		this.pattern = pattern;
		this.textAlignment = textAlignment;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public String getPattern() {
		return pattern;
	}

	public void setPattern(String pattern) {
		this.pattern = pattern;
	}

	public String getTextAlignment() {
		return textAlignment;
	}

	public void setTextAlignment(String textAlignment) {
		this.textAlignment = textAlignment;
	}
}
