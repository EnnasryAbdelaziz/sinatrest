package eai.devass.edition.Utils.beans;

public class SummaryBean {
	Integer width;
	RecapColumnBean[] recapsColumns;
	private VariableBean[] variables;
	
	public Integer getWidth() {
		return width;
	}
	public void setWidth(Integer width) {
		this.width = width;
	}
	public RecapColumnBean[] getRecapsColumns() {
		return recapsColumns;
	}
	public void setRecapsColumns(RecapColumnBean[] recapsColumns) {
		this.recapsColumns = (RecapColumnBean[])recapsColumns.clone();
	}
	public VariableBean[] getVariables() {
		return variables;
	}
	public void setVariables(VariableBean[] variables) {
		this.variables = (VariableBean[])variables.clone();
	}
}
