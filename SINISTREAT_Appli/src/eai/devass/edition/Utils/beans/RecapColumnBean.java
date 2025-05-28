package eai.devass.edition.Utils.beans;

public class RecapColumnBean {
	private String name;
	private FieldBean refField;
	private String calculation;
	public RecapColumnBean(String name, FieldBean refField, String calculation) {
		this.name = name;
		this.refField = refField;
		this.calculation = calculation;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public FieldBean getRefField() {
		return refField;
	}
	public void setRefField(FieldBean refField) {
		this.refField = refField;
	}
	public String getCalculation() {
		return calculation;
	}
	public void setCalculation(String calculation) {
		this.calculation = calculation;
	}
}
