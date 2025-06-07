package eai.devass.edition.Utils.beans;

public class FieldBean extends ReportParameterBean{
	private Integer width;
	public FieldBean(String name, String type, String libelle, String pattern, String textAlignment, Integer width) {
		super(name, type, libelle, pattern, textAlignment);
		this.width = width;
	}
	
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
}
